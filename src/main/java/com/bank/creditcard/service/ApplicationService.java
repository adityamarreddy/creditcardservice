package com.bank.creditcard.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.creditcard.builder.ApplicationBuilder;
import com.bank.creditcard.dao.model.CreditCardEntity;
import com.bank.creditcard.dao.model.UserEntity;
import com.bank.creditcard.exception.CreditCardApplicationException;
import com.bank.creditcard.generator.NumGenerator;
import com.bank.creditcard.jpa.repository.ApplicationRepo;
import com.bank.creditcard.jpa.repository.UserRepo;
import com.bank.creditcard.model.ApplicationRequest;
import com.bank.creditcard.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationService {

	private static final int MIN_RANK = 4;
	@Autowired
	private ApplicationRepo applicationRepo;
	@Autowired
	private UserRepo uesrRepo;
	@Autowired
	private ObjectMapper jsonMapper;

	@Autowired
	private ApplicationBuilder applicationBuilder;

	public User validateAndProcessApplication(ApplicationRequest application) {

		persistCreditCardApplication(application);
		UserEntity user = getDecisionOnCreditCardApplicaiton(application);
		try {
			log.info("Applicaiton process response is:{}", jsonMapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			log.info("Json marshal exception");
		}
		return applicationBuilder.buildResponseFromUserEntity(user);
	}

	private UserEntity getDecisionOnCreditCardApplicaiton(ApplicationRequest application) {
		// Making a random decision for the poc purpose
		int userRank = NumGenerator.generateRandomNumber();
		log.info("User evaluation rank is {}",userRank);
		if (userRank > MIN_RANK) {
			return processApplication(application);
		} else {
			throw new CreditCardApplicationException(
					"Application Declined: Not eligible to proceed further at this moment");
		}

	}

	private UserEntity processApplication(ApplicationRequest application) {
		log.info("processing application");
		long newCreditCardNumber = NumGenerator.generateCreditCardNumber();
		while (!applicationRepo.existsById(application.getApplicationId())) {
			newCreditCardNumber = NumGenerator.generateCreditCardNumber();
		}
		log.info("CreditCard Number associated with application is:{} is:{}",newCreditCardNumber, application.getApplicationId());
		CreditCardEntity creditCard = applicationBuilder.buildCreditCard(newCreditCardNumber,
				NumGenerator.generateRandomNumber() % 3);
		UserEntity user = applicationBuilder.buildUserFromApplicationAndCreditCard(creditCard, application);
		return persistUser(user);

	}

	@Transactional
	private UserEntity persistUser(UserEntity user) {
		return uesrRepo.save(user);

	}

	@Transactional
	private void persistCreditCardApplication(ApplicationRequest application) {
		if (!applicationRepo.existsById(application.getApplicationId())) {
			applicationRepo.save(applicationBuilder.buildApplicationEnityFromApplication(application));
		} else {
			throw new CreditCardApplicationException("Duplicate application");

		}

	}

}
