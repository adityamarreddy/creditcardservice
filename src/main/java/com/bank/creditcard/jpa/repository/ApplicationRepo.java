package com.bank.creditcard.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.creditcard.dao.model.ApplicationEntity;

@Repository
public interface ApplicationRepo extends CrudRepository<ApplicationEntity, Long>{

}
