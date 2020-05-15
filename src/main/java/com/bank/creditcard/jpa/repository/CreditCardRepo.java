package com.bank.creditcard.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.creditcard.dao.model.CreditCardEntity;

@Repository
@Transactional
public interface CreditCardRepo extends CrudRepository<CreditCardEntity, Long> {
	
	public List<CreditCardEntity> findByCardNumberAndExpiryMonthAndExpiryYear(long cardNumber,int expiryMonth, int expiryYear);
}
