package com.skipthedishes.hackathon.orderservice.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skipthedishes.hackathon.orderservice.controller.dto.ProductDto;

@FeignClient(name = "api-gateway")
@RibbonClient(name = "product-service")
public interface ProductServiceProxy {

    @GetMapping("/product-service/products/{id}")
    ProductDto retrieveProduct(@PathVariable("id") Long id);
}
