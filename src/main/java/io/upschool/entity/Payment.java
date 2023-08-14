package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name= "payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name= "passenger_name_surname", nullable = false, length = 50)
    private String passengerNameSurname;

    @Column (name= "price", nullable = false, length = 10)
    private Float price;

    @Column (name= "credit_card_number", nullable = false, length = 16)
    private String creditCardNumber;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = Boolean.TRUE;

}
