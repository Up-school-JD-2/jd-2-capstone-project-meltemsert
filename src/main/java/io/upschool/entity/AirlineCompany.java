package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "airline_company")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "active = true")
public class AirlineCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "iata_code", unique = true, nullable = false, length = 5)
    private String iataCode;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;


}
