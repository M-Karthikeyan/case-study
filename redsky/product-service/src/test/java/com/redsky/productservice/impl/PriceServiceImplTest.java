package com.redsky.productservice.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import com.redsky.productservice.domain.Price;
import com.redsky.productservice.repository.PriceRepository;
import com.redsky.productservice.service.ExternalAPIService;
import com.redsky.productservice.service.impl.PriceServiceImpl;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceImplTest {

	@InjectMocks
	private PriceServiceImpl priceService;

	@Mock
	private PriceRepository priceRepository;

	@Mock
	private ExternalAPIService externalAPIService;

	private Price price;

	private long productId = 1000000;

	@Before
	public void setUp() {
		price = new Price(productId, (float) 3.99, "USD");
	}

	@Test
	public void updatePrice_Pass_Test() throws Exception {
		Mockito.when(priceRepository.findById(productId)).thenReturn(Optional.of(price));

		boolean result = priceService.updatePrice(productId, price);

		Assert.assertTrue(result);
	}

	@Test
	public void updatePrice_Fails_Missing_PId_Test() throws Exception {
		Mockito.when(priceRepository.findById(productId)).thenReturn(Optional.empty());

		boolean result = priceService.updatePrice(productId, price);

		Assert.assertFalse(result);
	}

	@Test(expected = Exception.class)
	public void updateProduct_ErrorHandling_Test() throws Exception {
		Mockito.when(priceRepository.findById(productId)).thenReturn(null);

		boolean result = priceService.updatePrice(productId, price);

		Assert.assertFalse(result);
	}

//	@Before
//	public void setUp() throws Exception {
//		productDTO = new ProductDTO();
//		price = new Price(productId, (float) 3.99, "USD");
//
//		Mockito.when(externalAPIService.getProductById(productId)).thenReturn(productDTO);
//	}
//
//	@Test
//	public void getProductById_Exists_Test() throws Exception {
//
//		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
//		Optional<Product> result = productService.getProductById(productId);
//
//		Assert.assertTrue(result.isPresent());
//	}
//
//	@Test
//	public void getProductById_NotExists_Test() throws Exception {
//		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());
//		Optional<Product> result = productService.getProductById(productId);
//
//		Assert.assertFalse(result.isPresent());
//	}
//
//	@Test(expected = Exception.class)
//	public void getProductById_ErrorHandling_Test() throws Exception {
//		Mockito.when(productRepository.findById(productId)).thenReturn(null);
//		Optional<Product> result = productService.getProductById(productId);
//
//		Assert.assertFalse(result.isPresent());
//	}
//

}
