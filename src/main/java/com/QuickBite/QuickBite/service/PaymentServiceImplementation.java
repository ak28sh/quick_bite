package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.model.Order;
import com.QuickBite.QuickBite.model.PaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplementation implements PaymentService{

//    @Value("${strip.api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse generatePaymentLink(Order order) throws StripeException {

        //add secret key
        Stripe.apiKey = stripeSecretKey;

        //create a payment session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment/success" + order.getId())
                .setCancelUrl("http://localhost:3000/cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("jpy")
                                .setUnitAmount((long) order.getTotalAmount()*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder() //set order amount in cents
                                        .setName("pizza")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);

        System.out.println("session _____ " + session);

        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());

        return res;

    }
}
