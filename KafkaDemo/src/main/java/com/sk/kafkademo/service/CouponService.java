package com.sk.kafkademo.service;

import com.sk.kafkademo.entity.Coupon;
import com.sk.kafkademo.model.CouponModel;

public interface CouponService {
	
	public Coupon createCoupon (CouponModel c) throws Exception;
	
	public boolean isValidCoupon (String couponText)  throws Exception ;
	
}
