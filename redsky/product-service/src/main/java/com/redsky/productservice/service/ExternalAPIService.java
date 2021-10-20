package com.redsky.productservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.redsky.productservice.dto.ProductDTO;

@Service
public class ExternalAPIService {
	Logger log = LoggerFactory.getLogger(ExternalAPIService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${redsky.productservice.url}")
	private String url;

	@Value("${redsky.api.keytoken}")
	private String keyToken;

	public ProductDTO getProductById(long productId) throws Exception {
		try {

			log.info("External service url - {}", url);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>("body", headers);

			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
					.queryParam("key", keyToken)
					.queryParam("tcin", productId);

			ResponseEntity<ProductDTO> productResponse = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity,
					ProductDTO.class);

			if (productResponse.getStatusCode() == HttpStatus.OK) {
				log.info("Response from external service {}", productResponse.getBody().toString());
				return productResponse.getBody();
			} else {
				log.error("Exception thrown from external service {}", productResponse.toString());
				throw new Exception(productResponse.toString());
			}
		} catch (RestClientException ex) {
			throw ex;
		}
	}
}
