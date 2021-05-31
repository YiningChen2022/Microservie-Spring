package com.yining.paymentservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yining.paymentservice.api.entity.Payment;
import com.yining.paymentservice.api.service.Paymentservice;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private Paymentservice service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.doPayment(payment);

    }
    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return service.findPaymentHistoryByOrderId(orderId);

    }
}
