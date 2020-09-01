package br.com.rodrigo.paymentservice.resources;

import br.com.rodrigo.paymentservice.domain.Payment;
import br.com.rodrigo.paymentservice.services.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        return service.findPaymentHistoryByOrderId(orderId);

    }




}
