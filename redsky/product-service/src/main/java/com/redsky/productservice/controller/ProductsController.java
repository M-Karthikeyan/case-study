package com.redsky.productservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import com.redsky.productservice.domain.Price;
import com.redsky.productservice.dto.ProductDTO;
import com.redsky.productservice.service.PriceService;
import com.redsky.productservice.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
	Logger log = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private PriceService priceService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable(value = "id") long id) {
		Optional<ProductDTO> product = null;
		try {
			product = productService.getProductById(id);

			if (product.isPresent()) {
				return new ResponseEntity<>(product.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			if (ex instanceof RestClientException) {

			}
			log.error("Error retrieving product : %s - Error %s", id, ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePrice(@PathVariable(value = "id") long id, @RequestBody Price price) {
		try {

			boolean result = priceService.updatePrice(id, price);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}

		} catch (Exception ex) {
			log.error("Error updating product : %s - Error %s", id, ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostConstruct
	private void loadInitialData() {
		priceService.loadInitialData();
	}
}
