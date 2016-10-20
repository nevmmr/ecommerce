package com.ecommerce.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.ecommerce.canonical.models.AggregateId;
import com.ecommerce.canonical.vo.CustomerData;
import com.ecommerce.canonical.vo.Money;
import com.ecommerce.ddd.abstracts.domain.BaseAggregateRoot;
import com.ecommerce.ddd.annotations.domain.AggregateRoot;
import com.ecommerce.ddd.annotations.domain.Function;
import com.ecommerce.ddd.annotations.domain.Invariant;
import com.ecommerce.ddd.annotations.domain.InvariantsList;
import com.ecommerce.domain.offer.DiscountPolicy;
import com.ecommerce.domain.vo.Discount;
import com.ecommerce.domain.vo.Offer;
import com.ecommerce.domain.vo.OfferItem;
import com.ecommerce.domain.vo.ReservedProduct;



@InvariantsList({
	"closed: closed reservation cano not be modified",
	"duplicates: can not add already added product, increase quantity instead",
})

@Entity
@AggregateRoot
public class Reservation extends BaseAggregateRoot{
	
	public enum ReservationStatus{
		OPENED, CLOSED
	}
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "reservation")
	@Fetch(FetchMode.JOIN)
	private List<ReservationItem> items;

	@Embedded
	private CustomerData clientData;

	private Date createDate;

	@SuppressWarnings("unused")
	protected Reservation() {}

	public Reservation(AggregateId aggregateId, ReservationStatus status, CustomerData clientData, Date createDate){
		this.aggregateId = aggregateId;
		this.status = status;
		this.clientData = clientData;
		this.createDate = createDate;
		this.items = new ArrayList<ReservationItem>();
	}

	@Invariant({"closed", "duplicates"})
	public void add(Product product, int quantity){
		if (isClosed())
			domainError("Reservation already closed");
		if (!product.isAvailabe())
			domainError("Product is no longer available");
		
		if (contains(product)){
			increase(product, quantity);			
		}
		else{
			addNew(product, quantity);
		}
	}


	private void addNew(Product product, int quantity) {
		ReservationItem item = new ReservationItem(product, quantity);
		items.add(item);
	}

	private void increase(Product product, int quantity) {
		for (ReservationItem item : items) {
			if (item.getProduct().equals(product)){
				item.changeQuantityBy(quantity);
				break;
			}
		}
	}

	public boolean contains(Product product) {
		for (ReservationItem item : items) {
			if (item.getProduct().equals(product))
				return true;
		}
		return false;
	}

	public boolean isClosed() {
		return status.equals(ReservationStatus.CLOSED);
	}
	
	@Invariant({"closed"})
	public void close(){
		if (isClosed())
			domainError("Reservation is already closed");
		status = ReservationStatus.CLOSED;
	}

	public List<ReservedProduct> getReservedProducts() {
		ArrayList<ReservedProduct> result = new ArrayList<ReservedProduct>(items.size());
		
		for (ReservationItem item : items) {
			result.add(new ReservedProduct(item.getProduct().getAggregateId(), item.getProduct().getName(), item.getQuantity(), calculateItemCost(item)));
		}
		
		return result;
	}
	
	private Money calculateItemCost(ReservationItem item){
		return item.getProduct().getPrice().multiplyBy(item.getQuantity());
	}

	

	public CustomerData getClientData() {
		return clientData;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public ReservationStatus getStatus() {
		return status;
	}

	@Function
	public Offer calculateOffer(DiscountPolicy discountPolicy) {
		List<OfferItem> availabeItems = new ArrayList<OfferItem>();
		List<OfferItem> unavailableItems = new ArrayList<OfferItem>();
		
		for (ReservationItem item : items) {						
			if (item.getProduct().isAvailabe()){
				Discount discount = discountPolicy.applyDiscount(item.getProduct(), item.getQuantity(), item.getProduct().getPrice());
				OfferItem offerItem = new OfferItem(item.getProduct().generateSnapshot(), item.getQuantity(), discount);
				
				availabeItems.add(offerItem);
			}
			else {
				OfferItem offerItem = new OfferItem(item.getProduct().generateSnapshot(), item.getQuantity());
				
				unavailableItems.add(offerItem);
			}
		}
		
		return new Offer(availabeItems, unavailableItems);
	}
}
