package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.model.Order;
import com.QuickBite.QuickBite.model.PaymentResponse;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public PaymentResponse generatePaymentLink(Order order) throws StripeException;
}
