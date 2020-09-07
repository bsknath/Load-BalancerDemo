package com.sk.kafkademo.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.kafkademo.entity.Coupon;
import com.sk.kafkademo.model.CouponModel;
import com.sk.kafkademo.repo.CouponRepo;
import com.sk.kafkademo.utill.DateUtill;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService{
	
	@Autowired CouponRepo couponRepo;
	
	@Autowired ObjectMapper mapper;

	@Override
	public Coupon createCoupon(CouponModel coupon) throws Exception {
		
		Coupon savedCoupon = new Coupon();
		
		Coupon c = new Coupon();
		c.setCouponId(coupon.getCouponId());
		c.setCouponText(coupon.getCouponText());
		c.setCreatedDate( DateUtill.getTime(coupon.getCreatedDate()) );
		c.setValidTill(DateUtill.getTime(coupon.getValidTill()));
		
		try {
			savedCoupon = couponRepo.save(c);
			
			if (savedCoupon != null) {
				log.info("Coupon created || coupon-id", savedCoupon.getCouponId());
				return savedCoupon;
			} else {
				log.error("Error in ceating coupon");
				return savedCoupon;
			}
			
		}catch (Exception e) {
			log.error(e.getMessage());
			return savedCoupon;
		}
		
		
	}

	@Override
	public boolean isValidCoupon(String couponText) throws Exception {

		Coupon coupon = new Coupon();
		
		try {
			
			coupon = couponRepo.findByCouponText(couponText);
			if (coupon == null) {
				log.error("Invalid Coupon");
			} else {
				if (coupon.getValidTill().before(new Timestamp(System.currentTimeMillis()))) {
					log.error("Coupon Expired");
				} else {
					log.info("Valid Coupon");
					return true;
				}
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return false;
	}
	

}
