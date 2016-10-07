package com.mobapp.model;

import com.mobapp.interfaces.PaymentMethodInterface;

/**
 * Created by Roman Nevmerzhitskiy on 07.10.2016.
 */
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

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionResponse() {
        return transactionResponse;
    }

    public void setTransactionResponse(String transactionResponse) {
        this.transactionResponse = transactionResponse;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }


}
