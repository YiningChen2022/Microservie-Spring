package com.yining.paymentservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yining.paymentservice.api.entity.Payment;
import com.yining.paymentservice.api.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class Paymentservice {
    @Autowired
    private PaymentRepository repository;
    private Logger log= LoggerFactory.getLogger(Paymentservice.class);
    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("PaymentService request:{}",new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }
    public String paymentProcessing(){
        //we set it to random just for the text purpose
        //api should be 3 rd party payment gateway(paypal)
       return new Random().nextBoolean()?"success":"false";
    }
    public Payment findPaymentHistoryByOrderId (int orderId) throws JsonProcessingException {
        Payment payment=repository.findByOrderId(orderId);
        log.info("PAYMENTService  findPaymentHistoryByOrderId:{}",new ObjectMapper().writeValueAsString(payment));
        return repository.findByOrderId(orderId);
    }

}
