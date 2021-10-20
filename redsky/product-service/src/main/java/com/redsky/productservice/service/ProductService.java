package com.redsky.productservice.service;

import java.util.Optional;

import com.redsky.productservice.dto.ProductDTO;


public interface ProductService
{
    Optional<ProductDTO> getProductById(long productId) throws Exception;
}
