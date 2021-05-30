package com.yining.orderservice.api.service;

import com.yining.orderservice.api.common.Payment;
import com.yining.orderservice.api.common.TransactionRequest;
import com.yining.orderservice.api.common.TransactionResponse;
import com.yining.orderservice.api.entity.Order;
import com.yining.orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class Orderservice {
    @Autowired
    private OrderRepository repository;
    @Autowired


    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call:
        Payment paymentResponse=template.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment,Payment.class);

        response=paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is a failure in payment api, order added to cart";
         repository.save(order);
         return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);

    }



}
