package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "passenger")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", nullable = false, length = 20)
    private String name;

    @Column(name= "surname", nullable = false, length = 20)
    private String surname;

    @Column(name= "identification_number", unique = true, nullable = false, length =11)
    private String identificationNumber;

    @Column(name= "email", unique = true, nullable = false, length = 30)
    private String email;

    @Column(name= "contact_number", unique = true, nullable = false, length =10)
    private String contactNumber;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = Boolean.TRUE;

}
