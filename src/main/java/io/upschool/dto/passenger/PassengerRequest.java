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
    @Size(min = 3, max = 20, message = "The name must be {min} and {max} characters long!")
    private String name;

    @NotBlank
    @Size(min = 2, max = 30, message = "The name must be {min} and {max} characters long!")
    private String surName;

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Make sure that the ID number is exactly 11 digits long!")
    private String identificationNumber;

    @NotBlank
    @Email(message = "Make sure that  the e-mail consist of {@}!")
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Make sure that the contact number number is exactly 10 digits long!")
    private String contactNumber;
}
