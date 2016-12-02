package com.mobapp.model;

import com.mobapp.interfaces.PaymentMethodInterface;

public class StripeMethod implements PaymentMethodInterface {
	private String paymentName;
	private String apiToken;
	private String creditCardNumber;
	private String getCreditCardOwner;
	private int creditCardExpirationYear;
	private int creditCardExpirationMonth;
	private int creditCardSecurity;
	private String transactionId;
	private String transactionResponse;
	private String transactionStatus;

	public StripeMethod(String apiToken2, String creditCardNumber2, String creditCardOwner,
			int creditCardExpirationYear2, int creditCardExpirationMonth2, int creditCardSecurity2) {
		// TODO Auto-generated constructor stub
	}

	public String getPaymentName() {
		return paymentName;
	}

	public String getApiToken() {
		return apiToken;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getGetCreditCardOwner() {
		return getCreditCardOwner;
	}

	public int getCreditCardExpirationYear() {
		return creditCardExpirationYear;
	}

	public int getCreditCardExpirationMonth() {
		return creditCardExpirationMonth;
	}

	public int getCreditCardSecurity() {
		return creditCardSecurity;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getTransactionResponse() {
		return transactionResponse;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

}
