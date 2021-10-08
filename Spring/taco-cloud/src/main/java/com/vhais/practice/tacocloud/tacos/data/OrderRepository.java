package com.vhais.practice.tacocloud.tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vhais.practice.tacocloud.tacos.TacoOrder;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
	List<TacoOrder> findByDeliveryZip(String deliveryZip);
	List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
	List<TacoOrder> findByUserOrderByPlacedAtDesc(String username, Pageable pageable);
}
