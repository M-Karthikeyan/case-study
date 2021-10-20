package com.redsky.api.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController
{
    @GetMapping("/productServiceFallBack")
    public String productServiceFallBack()
    {
        return "Product service is not responding at this moment." + " Please try again later.";
    }

}
