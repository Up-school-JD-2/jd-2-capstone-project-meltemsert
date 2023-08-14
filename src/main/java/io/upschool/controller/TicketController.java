package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.ticket.TicketRequest;
import io.upschool.dto.ticket.TicketResponse;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<Object> getTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();

        BaseResponse response = BaseResponse.<List<TicketResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchTicket(@RequestParam String ticketNumber) {
        TicketResponse ticket = ticketService.searchTicket(ticketNumber);

        BaseResponse response = BaseResponse.<TicketResponse>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<Object> createTicket(@Valid @RequestBody TicketRequest request) {
        TicketResponse ticket = ticketService.save(request);

        BaseResponse response = BaseResponse.<TicketResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<BaseResponse<String>> deleteTicket(@PathVariable("id") Long id) {
        ticketService.cancelTicket(id);
        BaseResponse response = BaseResponse.<String>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data("Ticket canceled")
                .build();
        return ResponseEntity.ok(response);
    }


}
