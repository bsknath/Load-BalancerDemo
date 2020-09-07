package com.sk.kafkademo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "COUPON")
@Data
public class Coupon {

	@Id
	@Column(name = "COUPON_ID")
	private Long couponId;
	
	@Column(name = "COUPON_TEXT")
	private String couponText;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy HH:mm:ss.SSS")
	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy HH:mm:ss.SSS")
	@Column(name = "VALID_TILL")
	private Timestamp validTill;
	
}
