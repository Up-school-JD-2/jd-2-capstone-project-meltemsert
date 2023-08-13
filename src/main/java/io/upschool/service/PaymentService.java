package io.upschool.service;

import io.upschool.dto.payment.PaymentRequest;
import io.upschool.dto.payment.PaymentResponse;
import io.upschool.dto.ticket.TicketResponse;
import io.upschool.entity.Passenger;
import io.upschool.entity.Payment;
import io.upschool.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment save(PaymentRequest request , String  nameSurname ,Float price) {
        Payment payment = Payment.builder()
                .creditCardNumber(request.getCreditCardNumber())
                .passengerNameSurname(nameSurname)
                .price(price)
                .build();

        return paymentRepository.save(payment);

    }
    public PaymentResponse entityToResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .creditCardNumber(maskData(removeCharacters(payment.getCreditCardNumber())))
                .build();

    }

    private String removeCharacters(String cardNumber){
        return  cardNumber.replaceAll("[^0-9]", "");
    }

    private String maskData(String cardNumber) {
        return cardNumber.replaceAll(".(?=.{4})", "*");
    }
}
