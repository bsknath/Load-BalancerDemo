package com.sk.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoadController {

	@Autowired RestTemplate restTemplate;
	
	@GetMapping("/redeemCoupon")
	public String redeemCoupon(@RequestParam ("couponText") String couponText) {
		String url = "http://kafka-application/api/redeemCoupon?couponText="+couponText ;
		return restTemplate.getForObject(url, String.class);
	}
	
	@GetMapping("/useCoupon") 
	public String useCoupon (@RequestParam ("couponText") String couponText) {
		String url = "http://kafka-application/api/useCoupon?couponText="+couponText ;
		return restTemplate.getForObject(url, String.class);
	}
	
	
}
