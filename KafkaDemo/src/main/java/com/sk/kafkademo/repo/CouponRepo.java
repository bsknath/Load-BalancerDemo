package com.sk.kafkademo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.kafkademo.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Long>{
	
	public Coupon findByCouponText (String couponText);

}
