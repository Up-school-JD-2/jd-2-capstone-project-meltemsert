package io.upschool.service;

import io.upschool.dto.creditcard.CreditCardRequest;
import io.upschool.dto.creditcard.CreditCardResponse;
import io.upschool.entity.CreditCard;
import io.upschool.entity.Flight;
import io.upschool.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final CreditCardRepository creditCartRepository;
    private final PassengerService passengerService;

    public CreditCardResponse save(CreditCardRequest request) {

        CreditCard creditCardResponse = buildCreditCardAndSave(request);

        return CreditCardResponse.builder()
                .id(creditCardResponse.getId())
                .cvv(creditCardResponse.getCvv())
                .cardNumber(creditCardResponse.getCardNumber())
                .expiredMonth(creditCardResponse.getExpiredMonth())
                .expiredYear(creditCardResponse.getExpiredYear())
                .passengerNameSurname(creditCardResponse.getPassenger().getName()
                        + " " + creditCardResponse.getPassenger().getSurname())
                .build();
    }
    @Transactional(readOnly = true)
    public CreditCard getReferenceById(Long creditCardId) {
        return creditCartRepository.getReferenceById(creditCardId);
    }

    private CreditCard buildCreditCardAndSave(CreditCardRequest request) {

        CreditCard creditCard = CreditCard.builder()
                .cvv(request.getCvv())
                .cardNumber(maskCardNumber(formatCardNumber(request.getCardNumber())))
                .expiredMonth(request.getExpiredMonth())
                .expiredYear(request.getExpiredYear())
                .passenger(passengerService.getReferenceById(request.getPassengerId()))
                .build();

        return creditCartRepository.save(creditCard);
    }

    private String formatCardNumber(String cardNumber) {
        return cardNumber.replaceAll("[^0-9]", "");
    }

    private String maskCardNumber(String cardNumber) {
        return cardNumber.replaceAll(".(?=.{4})", "*");

    }


}
