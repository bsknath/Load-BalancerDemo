package com.sk.kafkademo.service;

import java.util.List;

import com.sk.kafkademo.entity.Client;
import com.sk.kafkademo.utill.ModelApiResponse;

public interface ClientService {
	
	public ModelApiResponse useCoupon (String couponText) throws Exception;
	
	public ModelApiResponse redeemCoupon (String couponText) throws Exception;
	
	public List<Client> getAllClient ();

}
