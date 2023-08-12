package io.upschool.controller;

import io.upschool.dto.creditcard.CreditCardRequest;
import io.upschool.dto.creditcard.CreditCardResponse;
import io.upschool.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCartService;

    @GetMapping
    public ResponseEntity<List<CreditCardResponse>> getCreditCart(){
        return null;
    }

    @PostMapping
    public ResponseEntity<CreditCardResponse> createCreditCart(@Valid @RequestBody CreditCardRequest request){
        var response=creditCartService.save(request);
        return ResponseEntity.ok(response);
    }


}
