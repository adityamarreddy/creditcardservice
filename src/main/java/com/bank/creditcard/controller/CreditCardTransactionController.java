package com.bank.creditcard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.creditcard.model.CreditCard;
import com.bank.creditcard.model.CreditCardTransactionRequest;
import com.bank.creditcard.model.CreditCardTransactionResponse;
import com.bank.creditcard.service.CreditCardTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/creditcard")
@Slf4j
public class CreditCardTransactionController {

	@Autowired
	private CreditCardTransactionService service;
	@Autowired
	private ObjectMapper jsonMapper;

	@PostMapping("/trasaction")
	public ResponseEntity<CreditCardTransactionResponse> processTransaction(
			@Valid @RequestBody CreditCardTransactionRequest request) {

		try {
			log.info("Application recieved: {}", jsonMapper.writeValueAsString(request));
		} catch (JsonProcessingException e) {
		}
		CreditCard card = service.processTransaction(request);
		return new ResponseEntity<>(CreditCardTransactionResponse.builder().status("Success").card(card)
				.message("Transaction Successful for TransactionId:" + request.getRequestTransactonId()).build(),
				HttpStatus.OK);
	}
}
