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
    public ResponseEntity<Object> getTickets(){
       var tickets= ticketService.getAllTickets();
        var response= BaseResponse.<List<TicketResponse>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTicket(@Valid @RequestBody TicketRequest request){
        var ticket= ticketService.save(request);
        var response=BaseResponse.<TicketResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public void deleteTicket(@PathVariable("id") Long id){
        ticketService.cancelTicket(id);
    }


}
