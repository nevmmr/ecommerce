package com.mobapp.manager;

import com.mobapp.interfaces.PaymentMethodInterface;

/**
 * Created by Roman Nevmerzhitskiy on 07.10.2016.
 */
public class StripeManager {
    public void processPayment(PaymentMethodInterface pm, double amount)
    {

    }

    private PaymentMethodInterface prepareData(PaymentMethodInterface pm, double amount)
    {
        return pm;
    }
}
