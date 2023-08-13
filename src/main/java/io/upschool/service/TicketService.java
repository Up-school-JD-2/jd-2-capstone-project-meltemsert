package io.upschool.service;

import io.upschool.dto.passenger.PassengerRequest;
import io.upschool.dto.passenger.PassengerResponse;
import io.upschool.dto.payment.PaymentRequest;
import io.upschool.dto.payment.PaymentResponse;
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
    private final PaymentService paymentService;


    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll().stream().map(ticket -> entityToResponse(ticket)).toList();

    }


    @Transactional
    public TicketResponse save(TicketRequest request) {
        PassengerRequest passengerRequest = request.getPassengerRequest();
        PaymentRequest paymentRequest = request.getPaymentRequest();
        Long flightId = request.getFlightId();
        Flight flight = flightService.getReferenceById(flightId);



        Passenger passenger = passengerService.save(passengerRequest);
        String nameSurname=passenger.getName() + " " + passenger.getSurname();
        Float price= flight.getPrice();
        Payment payment = paymentService.save(paymentRequest, nameSurname, price);



        int ticketCount = ticketRepository.countByFlightIdAndTicketStatus(request.getFlightId(), TicketStatus.COMPLETED);
        if (ticketCount >= flightService.getReferenceById(request.getFlightId()).getCapacity()) {
            throw new FlightCapacityExceededException("Tickets for this flight are sold out.");
        }

        Ticket ticket = Ticket.builder()
                .payment(payment)
                .ticketStatus(TicketStatus.valueOf(request.getTicketStatus()))
                .passenger(passenger)
                .flight(flight)
                .build();
        Ticket savedTicket = ticketRepository.save(ticket);

        return entityToResponse(savedTicket);


    }
    public void cancelTicket(Long id){
        Ticket ticket=ticketRepository.getReferenceById(id);
        ticket.setTicketStatus(TicketStatus.CANCELED);
        ticketRepository.save(ticket);
        System.out.println("The ticket has been cancelled.");
    }

    private TicketResponse entityToResponse(Ticket ticket){

        Payment payment = ticket.getPayment();
        Passenger passenger = ticket.getPassenger();

        PaymentResponse paymentResponse = paymentService.entityToResponse(payment);
        PassengerResponse passengerResponse = passengerService.entityToResponse(passenger);

        return TicketResponse.builder()
                .ticketNumber(ticket.getTicketNumber())
                .routeName(ticket.getFlight().getRoute().getRouteName())
                .ticketStatus(ticket.getTicketStatus().toString())
                .purchaseDateAndTime(ticket.getPurchaseDateAndTime())
                .passengerResponse(passengerResponse)
                .paymentResponse(paymentResponse)
                .build();
    }



}
