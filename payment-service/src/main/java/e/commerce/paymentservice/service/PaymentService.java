package e.commerce.paymentservice.service;

import e.commerce.paymentservice.kafka.request.PaymentRequest;

public interface PaymentService {

    void receivePayment(PaymentRequest request);
}
