package com.redsky.productservice.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.redsky.productservice.domain.Price;
import com.redsky.productservice.dto.ProductDTO;
import com.redsky.productservice.repository.PriceRepository;
import com.redsky.productservice.service.ExternalAPIService;
import com.redsky.productservice.service.PriceService;
import com.redsky.productservice.service.impl.ProductServiceImpl;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class ProductServieImplTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ExternalAPIService externalApiServie;
	
	@Mock
	private PriceRepository priceRepository;
	
	@Mock
	private PriceService priceService;

	private ProductDTO dto;

	private Price price;

	private long productId = 1000000;

	@Before
	public void setUp() throws Exception {
		dto = new ProductDTO();
		dto.setId(productId);

		price = new Price(productId, (float) 1.99, "USD");
	}

	@Test
	public void getProductById_Price_Exists_Test() throws Exception {
		Mockito.when(externalApiServie.getProductById(productId)).thenReturn(dto);
		Mockito.when(priceService.getPriceByProductId(productId)).thenReturn(Optional.of(price));
		
		Optional<ProductDTO> result = productService.getProductById(productId);
		
		Assert.assertTrue(result.isPresent());
	}
	
	@Test
	public void getProductById_Price_Not_Exists_Test() throws Exception {
		Mockito.when(externalApiServie.getProductById(productId)).thenReturn(dto);
		Mockito.when(priceService.getPriceByProductId(productId)).thenReturn(Optional.empty());
		
		Optional<ProductDTO> result = productService.getProductById(productId);
		
		Assert.assertFalse(result.isPresent());
	}
	
	@Test(expected = Exception.class)
	public void getProductById_ErrorHandling_Test() throws Exception {
		Mockito.when(externalApiServie.getProductById(productId)).thenReturn(dto);
		Mockito.when(priceService.getPriceByProductId(productId)).thenReturn(null);
		
		Optional<ProductDTO> result = productService.getProductById(productId);
		
		Assert.assertFalse(result.isPresent());
	}

}
