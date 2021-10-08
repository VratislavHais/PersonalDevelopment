package com.vhais.practice.tacocloud.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.vhais.practice.tacocloud.tacos.OrderProps;
import com.vhais.practice.tacocloud.tacos.TacoOrder;
import com.vhais.practice.tacocloud.tacos.User;
import com.vhais.practice.tacocloud.tacos.data.OrderRepository;

import javax.validation.Valid;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@Slf4j
public class OrderController {
	private OrderRepository orderRepository;
	private OrderProps orderProps;

	@Autowired
	public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
		this.orderRepository = orderRepository;
		this.orderProps = orderProps;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("tacoOrder", new TacoOrder());
		return "orderForm";
	}

	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
		model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user.getUsername(), pageable));
		return "orderList";
	}

	@PostMapping
	public String processOrder(@AuthenticationPrincipal User user, @Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		order.setPlacedAt(new Date());
		order.setUser(user.getUsername());
		orderRepository.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
