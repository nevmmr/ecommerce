package com.mobapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

  
    @RequestMapping("/order/{cartId}")
    public String createOrder(@PathVariable("cartId") int cartId){
     

    }

}
