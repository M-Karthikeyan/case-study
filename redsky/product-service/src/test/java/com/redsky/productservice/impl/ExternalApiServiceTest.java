package com.redsky.productservice.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.redsky.productservice.dto.ProductDTO;
import com.redsky.productservice.service.ExternalAPIService;


@RunWith(MockitoJUnitRunner.class)
public class ExternalApiServiceTest
{
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExternalAPIService externalAPIService;

    private long productId = 1000000;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(externalAPIService, "url", "http://testurl.com/%s");
    }

    @Test
    public void getProductById_OK_Test() throws Exception
    {
        ProductDTO productDTO = new ProductDTO();

        ResponseEntity<ProductDTO> response = new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);

        Mockito.when(restTemplate
                .exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<ProductDTO>>any())).thenReturn(response);

        productDTO = externalAPIService.getProductById(productId);

        Assert.assertNotNull(productDTO);
    }

    @Test(expected = Exception.class)
    public void getProductById_NotOk_Test() throws Exception
    {
        ProductDTO productDTO = new ProductDTO();
        ResponseEntity<ProductDTO> response = new ResponseEntity<ProductDTO>(productDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(restTemplate
                .exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<ProductDTO>>any())).thenReturn(response);

        productDTO = externalAPIService.getProductById(productId);

        Assert.assertNotNull(productDTO);
    }


    /*@Test(expected = RestClientException.class) public void getProductById_Server_Error_Test() throws Exception
    {
        ProductDTO productDTO = new ProductDTO();
       *//* ResponseEntity<ProductDTO> response = new ResponseEntity<ProductDTO>(productDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(restTemplate
                .exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<ProductDTO>>any())).thenReturn(response);*//*

        MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);

        server.expect(request -> {Mockito.any()}, )

        productDTO = externalAPIService.getProductById(productId);

        Assert.assertNotNull(productDTO);
    }*/

}
