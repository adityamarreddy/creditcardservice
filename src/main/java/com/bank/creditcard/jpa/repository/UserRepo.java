package com.bank.creditcard.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.creditcard.dao.model.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

}
