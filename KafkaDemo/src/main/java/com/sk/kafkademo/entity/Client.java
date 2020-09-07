package com.sk.kafkademo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "CLIENT")
@Data
public class Client {
	
	@Id
	@Column(name = "CLIENT_ID")
	private Long clientId;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "USED_COUPON")
	private String usedCoupon;
	
	@Column(name = "COUPON_REDEEM_AT")
	private Timestamp couponRedeemAt;
	
	@Column(name = "COUPON_USED_AT")
	private Timestamp couponUsedAt;
	
	@Column(name = "COUPON_VALID_TILL")
	private Timestamp couponValidTill;
	
	@Column(name = "IS_COUPON_USED")
	private String isCouponUsed;
	
	@Column(name = "COUPON_STATUS")
	private String couponStatus;

}
