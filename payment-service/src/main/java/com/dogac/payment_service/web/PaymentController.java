package com.dogac.payment_service.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogac.payment_service.application.bus.CommandBus;
import com.dogac.payment_service.application.bus.QueryBus;
import com.dogac.payment_service.application.commands.CreatePendingPaymentCommand;
import com.dogac.payment_service.application.dto.CreatedPaymentResponse;
import com.dogac.payment_service.application.dto.PaymentResponse;
import com.dogac.payment_service.application.queries.GetAllPaymentsQuery;
import com.dogac.payment_service.application.queries.GetPaymentByIdQuery;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PaymentController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping
    public ResponseEntity<CreatedPaymentResponse> createPayment(@RequestBody CreatePendingPaymentCommand command) {
        CreatedPaymentResponse response = commandBus.send(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable UUID id) {
        PaymentResponse response = queryBus.execute(new GetPaymentByIdQuery(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getPayments() {
        List<PaymentResponse> responseList = queryBus.execute(new GetAllPaymentsQuery());
        return ResponseEntity.ok(responseList);
    }
}
