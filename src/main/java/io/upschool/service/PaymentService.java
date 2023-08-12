package io.upschool.service;

import io.upschool.dto.creditcard.CreditCardRequest;
import io.upschool.dto.creditcard.CreditCardResponse;
import io.upschool.dto.payment.PaymentRequest;
import io.upschool.dto.payment.PaymentResponse;
import io.upschool.entity.Payment;
import io.upschool.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CreditCardService creditCardService;


    public PaymentResponse save(PaymentRequest request) {

        Payment paymentResponse=buildPaymentAndSave(request);
        return PaymentResponse.builder()
                .id(paymentResponse.getId())
                .creditCardId(paymentResponse.getCreditCard().getId())
                .build();
    }

    private Payment buildPaymentAndSave(PaymentRequest request) {

        Payment payment=Payment.builder()
                .creditCard(creditCardService.getReferenceById(request.getCreditCardId()))
                .build();
        return paymentRepository.save(payment);
    }
}
