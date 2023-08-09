package io.upschool.dto.passenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSaveRequest {
    private String name;
    private String surName;
    private String identificationNumber;
    private String email;
    private String contactNumber;
}
