package com.QuickBite.QuickBite.controller;

import com.QuickBite.QuickBite.Exception.OrderException;
import com.QuickBite.QuickBite.model.Order;
import com.QuickBite.QuickBite.model.PaymentResponse;
import com.QuickBite.QuickBite.service.OrderService;
import com.QuickBite.QuickBite.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResponse> generatePaymentLink(
            @PathVariable Long orderId
    ) throws StripeException, OrderException {
        Order order = orderService.findOrderById(orderId);
        PaymentResponse res = paymentService.generatePaymentLink(order);
        return new ResponseEntity<PaymentResponse>(res, HttpStatus.ACCEPTED);
    }
}
