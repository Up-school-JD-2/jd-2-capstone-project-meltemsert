package io.upschool.controller;

import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketSaveResponse>> getTickets(){
       var response= ticketService.getAllTickets();
       return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TicketSaveResponse> createTicket(@RequestBody TicketSaveRequest request){
        var response= ticketService.save(request);
        return ResponseEntity.ok(response);
    }

}
