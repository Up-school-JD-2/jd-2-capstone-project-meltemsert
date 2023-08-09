package io.upschool.dto.passenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSaveResponse{
    private Long id;
    private String nameSurname;
    private String identificationNumber;
    private String email;
    private String contactNumber;

}
