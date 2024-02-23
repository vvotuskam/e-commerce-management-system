package e.commerce.paymentservice.serviceimpl;

import e.commerce.paymentservice.entity.PaymentEntity;
import e.commerce.paymentservice.kafka.request.PaymentRequest;
import e.commerce.paymentservice.repository.PaymentRepository;
import e.commerce.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void receivePayment(PaymentRequest request) {
        PaymentEntity payment = PaymentEntity.builder()
                .email(request.getEmail())
                .amount(request.getAmount())
                .paymentDate(request.getPaymentDate())
                .orderId(request.getOrder().getId())
                .build();

        paymentRepository.save(payment);
    }
}
