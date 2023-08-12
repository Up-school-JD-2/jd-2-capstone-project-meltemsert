package io.upschool.dto.passenger;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerRequest {
    @NotBlank
    @Size(min=3 , max= 20 , message = "İsminizi giriniz")
    private String name;

    @NotBlank
    @Size(min=2 , max= 30 , message = "Soy isminizi giriniz")
    private String surName;

    @Pattern(regexp = "\\d{11}" , message = "Geçerli bir kimlik numarası giriniz.")
    private String identificationNumber;

    @NotBlank
    @Email(message = "Hatalı email girildi.")
    private String email;

    @Pattern(regexp = "\\d{10}" , message = "Geçerli bir iletişim numarası giriniz." )
    private String contactNumber;
}
