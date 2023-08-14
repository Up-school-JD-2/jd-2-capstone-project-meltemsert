package io.upschool.repository;

import io.upschool.entity.Ticket;
import io.upschool.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    int countByFlightIdAndTicketStatus(Long id, TicketStatus ticketStatus);

    Ticket findByTicketNumber(String number);
}
