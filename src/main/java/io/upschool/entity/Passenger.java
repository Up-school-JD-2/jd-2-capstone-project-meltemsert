package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;


@Entity
@Data
@Table(name = "passengers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "İsim boş bırakılamaz. ")
    @Column(name= "name" , nullable = false , length = 20)
    private String name;

    @NotEmpty(message = "Soyisim boş bırakılamaz. ")
    @Column(name= "surname" , nullable = false , length = 20)
    private String surname;

    @Pattern(regexp = "[0-9\\s]{11}")
    @Column(name= "identification_number", unique = true, nullable = false , length =15)
    private String identificationNumber;

    @Email(message = "Hatalı email girildi.")
    @Column(name= "email" , nullable = false , length = 11)
    private String email;

    @Pattern(regexp = "[0-9\\s]{12}")
    @Column(name= "contact_number" , nullable = false , length =15)
    private String contactNumber;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;

}
