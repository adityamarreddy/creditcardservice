package com.bank.creditcard.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.creditcard.dao.model.CreditCardTransactionEntity;

@Repository
@Transactional
public interface CreditCardTransactionRepo extends CrudRepository<CreditCardTransactionEntity, Long> {

}
