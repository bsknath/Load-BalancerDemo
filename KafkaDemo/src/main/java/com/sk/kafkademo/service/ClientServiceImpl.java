package com.sk.kafkademo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sk.kafkademo.entity.Client;
import com.sk.kafkademo.repo.ClientRepo;
import com.sk.kafkademo.repo.CouponRepo;
import com.sk.kafkademo.utill.DateUtill;
import com.sk.kafkademo.utill.ModelApiResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService{

	@Autowired ClientRepo clientRepo;
	
	@Autowired CouponRepo couponRepo;
	
	@Autowired CouponService couponService;
	
	Random random = new Random();
	
	@Override
	public ModelApiResponse useCoupon(String couponText) throws Exception {

		Client client = new Client();
		ModelApiResponse response = new ModelApiResponse();
		try {

			if (couponService.isValidCoupon(couponText)) {

				client = clientRepo.findByUsedCoupon(couponText);
				if (client == null) {
					log.info("Redeem the coupon first");
					response.setBody("INVALID COUPON");
					response.setStatus(""+HttpStatus.BAD_REQUEST);
					response.setMessage("Redeem the coupon first");
					return response;
				} else if (client.getIsCouponUsed().equalsIgnoreCase("Y") ) {
					log.info("Coupon is already used");
					response.setBody("COUPON USED");
					response.setStatus(""+HttpStatus.BAD_REQUEST);
					response.setMessage("Coupon is already used");
					return response;
				} else if ( client.getCouponStatus().equalsIgnoreCase("EX") ) {
					log.info("Coupon Expired for this client");
					response.setBody("EXPIRED COUPON");
					response.setStatus(""+HttpStatus.BAD_REQUEST);
					response.setMessage("Coupon is Expired");
					return response;
				} else {
					log.info("Coupon Used");
					client.setCouponUsedAt(new Timestamp(System.currentTimeMillis()));
					client.setIsCouponUsed("Y");
					Client savedClient = clientRepo.save(client);
					if (savedClient != null) 
						log.info("Client record updated");
					else {
						throw new Exception("Client not uodated");
					}
					response.setBody("COUPON APPLIED");
					response.setStatus(""+HttpStatus.OK);
					response.setMessage("Coupon applied successfully");
					return response;
				}

			} else {
				return new ModelApiResponse(""+HttpStatus.BAD_REQUEST, "Coupon is not available", "INVALID COUPON");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return new ModelApiResponse(""+HttpStatus.BAD_REQUEST, e.getMessage(), "BAD REQUEST");
		}
	}


	@Override
	public ModelApiResponse redeemCoupon(String couponText) throws Exception {
		// TODO Auto-generated method stub
		Client client = new Client();
		ModelApiResponse response = new ModelApiResponse();
		try {

			if (couponService.isValidCoupon(couponText)) {

				client = clientRepo.findByUsedCoupon(couponText);
				if (client != null) {
					log.info("Coupon is already redeemed");
					response.setStatus(""+HttpStatus.BAD_REQUEST);
					response.setMessage("Coupon is already redeemed");
					response.setBody("REDEEM FAILED");
					return response;
				} else {
					Client newClinet = new Client();
					int x = random.nextInt(900) + 99;
					newClinet.setClientId( (long) x );
					newClinet.setUsedCoupon(couponText);
					newClinet.setClientName("Shesho-"+x);
					newClinet.setCouponRedeemAt(new Timestamp(System.currentTimeMillis()));
					newClinet.setCouponValidTill( DateUtill.getMidnightTime() );
					newClinet.setIsCouponUsed("N");
					
					Client savedClient = clientRepo.save(newClinet);
					if (savedClient == null) {
						log.error("Client not saved");
					}else {
						log.info("Client saved");
						response.setStatus(""+HttpStatus.OK);
						response.setMessage("Coupon redeemed");
						response.setBody("REDEEM SUCCESS");
						return response;
					}
					
				}
				
			}else {
				return new ModelApiResponse(""+HttpStatus.BAD_REQUEST, "Invalid Coupon", "REDEEM FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(""+HttpStatus.BAD_REQUEST);
			response.setMessage(e.getMessage());
			response.setBody("BAD_REQUEST");
		}
		return new ModelApiResponse(""+HttpStatus.BAD_GATEWAY, "Invalid Coupon", "REDEEM FAILED");
		
	}


	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return clientRepo.findAll();
	}
	

}
