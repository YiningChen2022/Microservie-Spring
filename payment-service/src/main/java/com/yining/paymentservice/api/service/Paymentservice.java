package com.yining.paymentservice.api.service;

import com.yining.paymentservice.api.entity.Payment;
import com.yining.paymentservice.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class Paymentservice {
    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }
    public String paymentProcessing(){
        //we set it to random just for the text purpose
        //api should be 3 rd party payment gateway(paypal)
       return new Random().nextBoolean()?"success":"false";
    }
    public Payment findPaymentHistoryByOrderId (int orderId){
        return repository.findByOrderId(orderId);
    }

}
