package com.ecommerce.system.eventhandlers;


public interface EventHandler {
    boolean canHandle(Object event);

    void handle(Object event);
}