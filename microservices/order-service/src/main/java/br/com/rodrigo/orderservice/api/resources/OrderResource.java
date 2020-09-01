package br.com.rodrigo.orderservice.api.resources;

import br.com.rodrigo.orderservice.api.commom.Payment;
import br.com.rodrigo.orderservice.api.commom.TransactionRequest;
import br.com.rodrigo.orderservice.api.commom.TransactionResponse;
import br.com.rodrigo.orderservice.api.domain.Order;
import br.com.rodrigo.orderservice.api.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrders")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {

        return orderService.saveOrder(request);
    }

}
