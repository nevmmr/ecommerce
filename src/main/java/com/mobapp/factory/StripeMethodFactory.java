package com.mobapp.factory;

import com.mobapp.model.StripeMethod;

/**
 * Created by Roman Nevmerzhitskiy on 07.10.2016.
 */
public class StripeMethodFactory {
    public StripeMethod create(String apiToken, String creditCardNumber, String creditCardOwner, int creditCardExpirationYear, int creditCardExpirationMonth,int creditCardSecurity)
    {
        // TODO: Create StripeMethod object
        StripeMethod sm = new StripeMethod();
        return sm;
    }
}
