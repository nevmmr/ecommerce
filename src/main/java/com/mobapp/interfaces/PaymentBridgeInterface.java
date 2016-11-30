package com.mobapp.interfaces;

import java.util.Date;

/**
 * Created by Roman Nevmerzhitskiy on 07.10.2016.
 */
public interface PaymentBridgeInterface {
	public void setOrder(Order order);

	public Order gerOrder();

	public int getOrderId();

	public boolean isOrderPaid();

	public double getAmount();

	public String getCurrency();

	public Date getExtraData();
}
