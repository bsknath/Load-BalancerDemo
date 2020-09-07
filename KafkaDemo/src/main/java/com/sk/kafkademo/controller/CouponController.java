package com.sk.kafkademo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.kafkademo.entity.Client;
import com.sk.kafkademo.entity.Coupon;
import com.sk.kafkademo.model.CouponModel;
import com.sk.kafkademo.service.ClientService;
import com.sk.kafkademo.service.CouponService;
import com.sk.kafkademo.utill.ModelApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CouponController {

	@Autowired ClientService clientService;
	
	@Autowired CouponService couponService;
	
	@Value("${server.port}")
	public String port;
	
	
	@GetMapping("/allClient")
	public List<Client> getAllClient () {
		return clientService.getAllClient();
	}
 	
	@GetMapping ("/test")
	public String getTest () {
		return "test";
	}
	
	@PostMapping("/addCoupon")
	public ResponseEntity<Coupon> createCoupon (@RequestBody CouponModel coupon) {
		
		try {
			return new ResponseEntity<Coupon>(couponService.createCoupon(coupon) , HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Coupon> (HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@GetMapping("/redeemCoupon") 
	public ResponseEntity<ModelApiResponse> redeemCoupon (@RequestParam ("couponText") String couponText) {
		log.info ("port number: "+port);
		try {
			return new ResponseEntity<ModelApiResponse>(clientService.redeemCoupon(couponText), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ModelApiResponse>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/useCoupon") 
	public ResponseEntity<ModelApiResponse> useCoupon (@RequestParam ("couponText") String couponText) {	
		log.info ("port number: "+ port);
		try {
			return new ResponseEntity<ModelApiResponse>(clientService.useCoupon(couponText), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ModelApiResponse>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
}
