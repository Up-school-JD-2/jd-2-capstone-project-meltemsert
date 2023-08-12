package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

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

    @Column(name = "ticket_number" , unique = true, nullable = false , length = 10)
    private String ticketNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "passenger_id" , nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "flight_id" , nullable = false)
    private Flight flight;

    @Column(name = "purchase_date_and_time" , nullable = false , length = 20)
    private LocalDateTime purchaseDateAndTime;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id" ,nullable = false)
    private Payment payment;

    @Column(name = "active")
    @Builder.Default
    private boolean active = Boolean.TRUE;
}
