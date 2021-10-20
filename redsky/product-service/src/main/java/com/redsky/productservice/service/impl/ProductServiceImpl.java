package com.redsky.productservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsky.productservice.domain.Price;
import com.redsky.productservice.dto.ProductDTO;
import com.redsky.productservice.service.ExternalAPIService;
import com.redsky.productservice.service.PriceService;
import com.redsky.productservice.service.ProductService;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ExternalAPIService externalApiService;

	@Autowired
	private PriceService priceService;

	@Override
	public Optional<ProductDTO> getProductById(long productId) throws Exception {

		ProductDTO dto = externalApiService.getProductById(productId);

		Optional<Price> price = priceService.getPriceByProductId(productId);

		if (price.isPresent()) {

			dto.setPrice(price.get().getPrice());
			dto.setCurrency(price.get().getCurrency());

			return Optional.of(dto);
		}

		log.debug("Price not found for Product - {}", productId);

		return Optional.empty();
	}
}
