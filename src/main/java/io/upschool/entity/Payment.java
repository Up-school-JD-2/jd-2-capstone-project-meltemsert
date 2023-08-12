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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "credit_card_id" , nullable = false)
    private CreditCard creditCard;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

}
