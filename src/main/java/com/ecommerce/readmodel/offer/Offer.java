package com.ecommerce.readmodel.offer;


import java.util.List;

import com.ecommerce.ddd.annotations.app.Finder;
import com.ecommerce.domain.dto.OfferedProductDto;

@Finder
public interface Offer {

	public List<OfferedProductDto> find(OfferQuery query);
}
