package com.mobapp.interfaces;

/**
 * Created by Roman Nevmerzhitskiy on 07.10.2016.
 */
abstract public class AbstractPaymentEvent {
    private PaymentBridgeInterface paymentBridge;
    private PaymentMethodInterface paymentMethod;

    public PaymentBridgeInterface getPaymentBridge() {
        return paymentBridge;
    }

    public PaymentMethodInterface getPaymentMethod() {
        return paymentMethod;
    }
}
