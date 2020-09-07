package com.sk.kafkademo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CouponModel {
	
	private Long couponId;
	
	private String couponText;
	
	private String createdDate;
	
	private String validTill;

}
