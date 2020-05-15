package com.bank.creditcard.builder;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.bank.creditcard.dao.model.BalanceEntity;
import com.bank.creditcard.dao.model.CreditCardEntity;
import com.bank.creditcard.dao.model.CreditCardTransactionEntity;
import com.bank.creditcard.model.Balance;
import com.bank.creditcard.model.CreditCard;
import com.bank.creditcard.model.CreditCardTransactionRequest;

@Component
public class CreditCardTransactionBuilder {

	public CreditCardTransactionEntity buildCreditCardTransactionFromRequest(CreditCardTransactionRequest request,
			CreditCardEntity entity) {

		return CreditCardTransactionEntity.builder().requestTransactonId(request.getRequestTransactonId())
				.transactionAmount(request.getAmount()).transactionType(request.getOperation().toString())
				.transactionDate(Timestamp.valueOf(request.getTransactionTimeStamp()))
				.cardNumber(entity.getCardNumber()).build();
	}

	public CreditCard buildCreditCardFromCreditCardEntity(CreditCardEntity entity) {
		CreditCard creditCard = CreditCard.builder().cardNumber(entity.getCardNumber()).build();
		creditCard.setBalance(buildBalanceFromBalanceEntity(entity.getBalance()));
		return creditCard;
	}

	private Balance buildBalanceFromBalanceEntity(BalanceEntity balance) {
		return Balance.builder().cardLimitAvailable(balance.getCardLimitAvailable()).cardLimit(balance.getCardLimit())
				.cashLimit(balance.getCashLimit()).cashLimitAvailable(balance.getCashLimit())
				.unbilledAmount(balance.getUnbilledAmount()).build();
	}
}
