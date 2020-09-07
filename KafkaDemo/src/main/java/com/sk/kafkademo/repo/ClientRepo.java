package com.sk.kafkademo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.kafkademo.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long>{
	
	public Client findByUsedCoupon (String usedCoupon);
	
	
	

}
