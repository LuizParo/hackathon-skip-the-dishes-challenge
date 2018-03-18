package com.skipthedishes.hackathon.orderservice.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skipthedishes.hackathon.orderservice.controller.dto.CustomerDto;

@FeignClient(name = "api-gateway")
@RibbonClient(name = "customer-service")
public interface CustomerServiceProxy {

    @GetMapping("/customer-service/customers/{id}")
    CustomerDto retrieveCustomer(@PathVariable("id") Long id);
}
