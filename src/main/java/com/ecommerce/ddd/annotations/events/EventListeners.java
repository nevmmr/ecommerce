package com.ecommerce.ddd.annotations.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}
