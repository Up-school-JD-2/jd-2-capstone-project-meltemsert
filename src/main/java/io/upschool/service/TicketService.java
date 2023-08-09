package io.upschool.service;

import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.entity.*;
import io.upschool.exception.FlightCapacityExceededException;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightService flightService;

    private final PassengerService passengerService;
    public List<TicketSaveResponse> getAllTickets() {
        return null;
    }

    public TicketSaveResponse save(TicketSaveRequest request) {
        int ticketCountById= ticketRepository.findTicketCountById();
        Flight flightByReference = flightService.getReferenceById(request.getFlightId());
        Passenger passengerByReference=passengerService.getReferenceById(request.getPassengerId());
        if(ticketCountById> flightByReference.getCapacity()){
            throw new FlightCapacityExceededException("Bu uçuş için bilet tükenmiştir.");
        }
        var newTicket= ticketRepository.save(Ticket.builder()
                .ticketNumber(request.getTicketNumber())
                .passenger(passengerByReference)
                .flight(flightByReference)
                .purchaseDateAndTime(request.getPurchaseDateAndTime())
                .ticketStatus(TicketStatus.valueOf(request.getTicketStatus()))
                .build());

        return TicketSaveResponse.builder()
                .id(newTicket.getId())
                .ticketNumber(newTicket.getTicketNumber())
                .passengerId(newTicket.getPassenger().getId())
                .flightId(newTicket.getFlight().getId())
                .purchaseDateAndTime(newTicket.getPurchaseDateAndTime())
                .ticketStatus(newTicket.getTicketStatus().toString())
                .build();

    }
}
