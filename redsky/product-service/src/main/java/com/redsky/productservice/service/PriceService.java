package com.redsky.productservice.service;

import java.util.Optional;

import com.redsky.productservice.domain.Price;

public interface PriceService {

	Optional<Price> getPriceByProductId(long productId) throws Exception;

	boolean updatePrice(long productId, Price price) throws Exception;

	void loadInitialData();
}