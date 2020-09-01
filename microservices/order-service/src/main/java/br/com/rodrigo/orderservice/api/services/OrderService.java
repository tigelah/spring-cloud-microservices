package br.com.rodrigo.orderservice.api.services;

import br.com.rodrigo.orderservice.api.commom.Payment;
import br.com.rodrigo.orderservice.api.commom.TransactionRequest;
import br.com.rodrigo.orderservice.api.commom.TransactionResponse;
import br.com.rodrigo.orderservice.api.domain.Order;
import br.com.rodrigo.orderservice.api.repositories.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String endPointUrl;

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {

        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));

        Payment paymentResponse =  template.postForObject(endPointUrl,payment,Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is a failure api, order added to cart";
        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
         orderRepository.save(order);
         return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}