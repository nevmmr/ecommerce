package com.mobapp.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mobapp.factory.StripeTransactionFactory;
import com.mobapp.interfaces.PaymentBridgeInterface;
import com.mobapp.interfaces.PaymentMethodInterface;

public class StripeManager {
	
	@Autowired
	PaymentBridgeInterface paymentBridge;
	
	@Autowired
	StripeTransactionFactory transactionFactory;
		
    public void processPayment(PaymentMethodInterface pm, double amount)
    {
    	// params = prepareData(pm,amount);
    	// transaction = transactionFactory.create(params);
    	
    	// notify payment order done
    	
    	// if (transaction[paid] !=1){
    	// notify payment order failed
        //}
    	 

    }

    private PaymentMethodInterface prepareData(PaymentMethodInterface pm, double amount)
    {
        return pm;
    }
}
