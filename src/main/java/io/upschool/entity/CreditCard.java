package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.YearMonth;

@Entity
@Table(name="credit_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "cvv" , unique = true ,nullable = false)
    private String cvv;

    @Column(name= "cart_number" , unique = true,nullable = false)
    private String cardNumber;

    @Column(name= "expiration_month" , nullable = false)
    private String expiredMonth;

    @Column(name= "expiration_year" ,  nullable = false)
    private String expiredYear;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="passenger_id", nullable = false)
    private Passenger passenger;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;
}
