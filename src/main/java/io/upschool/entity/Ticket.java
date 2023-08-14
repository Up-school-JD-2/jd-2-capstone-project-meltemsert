package io.upschool.entity;

import io.upschool.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name= "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "active = true" )
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number", unique = true, nullable = false, length = 10)
    @Builder.Default
    private String ticketNumber= UUID.randomUUID().toString().substring(0,10);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "purchase_date_and_time", nullable = false, length = 20)
    @Builder.Default
    private LocalDateTime purchaseDateAndTime= LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "active")
    @Builder.Default
    private Boolean active = Boolean.TRUE;
}
