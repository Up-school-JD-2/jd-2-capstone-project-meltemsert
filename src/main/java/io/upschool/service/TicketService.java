package io.upschool.service;

import io.upschool.dto.ticket.TicketRequest;
import io.upschool.dto.ticket.TicketResponse;
import io.upschool.entity.*;
import io.upschool.exception.FlightCapacityExceededException;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightService flightService;
    private final PassengerService passengerService;

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream().map(ticket -> TicketResponse.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .purchaseDateAndTime(ticket.getPurchaseDateAndTime())
                .ticketStatus(ticket.getTicketStatus().toString())
                .passengerId(ticket.getPassenger().getId())
                .nameSurname(ticket.getPassenger().getName()
                        + " " + ticket.getPassenger().getSurname())
                .flightId(ticket.getFlight().getId())
                .routeName(ticket.getFlight().getRoute().getRouteName())
                .build()).collect(Collectors.toList());

    }

    @Transactional
    public TicketResponse save(TicketRequest request) {

        int ticketCount = ticketRepository.countByFlightIdAndTicketStatus(request.getFlightId(), TicketStatus.COMPLETED);
        if (ticketCount >= flightService.getReferenceById(request.getFlightId()).getCapacity()) {
            throw new FlightCapacityExceededException("Tickets for this flight are sold out.");
        }
        Ticket ticketResponse=buildTicketAndSave(request);
        return TicketResponse.builder()
                .id(ticketResponse.getId())
                .ticketNumber(ticketResponse.getTicketNumber())
                .purchaseDateAndTime(ticketResponse.getPurchaseDateAndTime())
                .ticketStatus(ticketResponse.getTicketStatus().toString())
                .passengerId(ticketResponse.getPassenger().getId())
                .nameSurname(ticketResponse.getPassenger().getName()
                        + " " + ticketResponse.getPassenger().getSurname())
                .flightId(ticketResponse.getFlight().getId())
                .routeName(ticketResponse.getFlight().getRoute().getRouteName())
                .build();
    }

    private Ticket buildTicketAndSave(TicketRequest request) {

        Ticket ticket = Ticket.builder()
                .ticketNumber(request.getTicketNumber())
                .passenger(passengerService.getReferenceById(request.getPassengerId()))
                .flight( flightService.getReferenceById(request.getFlightId()))
                .purchaseDateAndTime(request.getPurchaseDateAndTime())
                .ticketStatus(TicketStatus.valueOf(request.getTicketStatus()))
                .build();
        return ticketRepository.save(ticket);
    }
}
