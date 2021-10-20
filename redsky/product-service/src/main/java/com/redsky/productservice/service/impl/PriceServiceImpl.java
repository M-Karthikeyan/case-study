package com.redsky.productservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redsky.productservice.domain.Price;
import com.redsky.productservice.repository.PriceRepository;
import com.redsky.productservice.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public Optional<Price> getPriceByProductId(long productId) throws Exception {

		return priceRepository.findById(productId);
	}

	@Override
	public boolean updatePrice(long productId, Price price) throws Exception {
		Optional<Price> result = priceRepository.findById(productId);

		if (result.isPresent()) {
			priceRepository.save(price);
			log.info("price updated successfully!");
			return true;
		}

		log.info("Product do not exists therefore update skipped");
		return false;
	}

	@Override
	public void loadInitialData() {
		try {
			List<Price> prices = new ArrayList<>();

			prices.add(new Price(13860428, (float) 2.99, "USD"));
			prices.add(new Price(54456119, (float) 3.99, "USD"));
			prices.add(new Price(13864003, (float) 5.99, "USD"));
			prices.add(new Price(12954218, (float) 10.99, "USD"));

			priceRepository.saveAll(prices);

			log.info("Initial data load is completed");
		} catch (Exception ex) {
			log.info("Problem loading initial data : {}", ex.getMessage());
			throw ex;
		}

	}

}
