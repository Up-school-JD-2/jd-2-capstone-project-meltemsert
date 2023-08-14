package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "route")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "route_name", unique = true, nullable = false, length = 70)
    private String routeName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "departure_airport_id", nullable = false)
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "airline_company_id", nullable = false)
    private AirlineCompany airlineCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "arrival_airport_id", nullable = false)
    private Airport arrivalAirport;

    @Column(name= "flight_time", nullable = false, length = 5)
    private Float flightTime;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = Boolean.TRUE;
}
