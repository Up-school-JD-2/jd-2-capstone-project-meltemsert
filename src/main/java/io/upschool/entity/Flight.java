package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;


@Entity
@Table(name="flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", unique = true, nullable = false, length = 10)
    private String flightNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "route_id", nullable = false)
    private Route route;

    @Column(name = "departure_date_and_time", nullable = false, length = 20)
    private LocalDateTime departureDateAndTime;

    @Column(name = "arrival_date_and_time", nullable = false, length = 20)
    private LocalDateTime arrivalDateAndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "airline_company_id", nullable = false)
    private AirlineCompany airlineCompany;

    @Column(name= "capacity")
    @Builder.Default
    private Integer capacity=3;

    @Column(name= "price", nullable = false, length = 10)
    private Float price;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = Boolean.TRUE;


}
