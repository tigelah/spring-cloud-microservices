package br.com.rodrigo.orderservice.api.repositories;

import br.com.rodrigo.orderservice.api.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<Order,Integer> {
}
