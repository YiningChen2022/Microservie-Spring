package com.yining.orderservice.api.controller;

import com.yining.orderservice.api.common.Payment;
import com.yining.orderservice.api.common.TransactionRequest;
import com.yining.orderservice.api.common.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yining.orderservice.api.entity.Order;
import com.yining.orderservice.api.service.Orderservice;
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private Orderservice service;
    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder (@RequestBody TransactionRequest request){
      return service.saveOrder(request);

   }
}
