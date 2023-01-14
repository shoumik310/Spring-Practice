package com.bajajfinserve.orders.controller;

import java.time.Duration;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bajajfinserve.orders.model.Order;
import com.bajajfinserve.orders.service.OrderService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderRestController {

	private final OrderService orderService;
	private final WebClient webClient;

	@GetMapping
	//@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Map<String, Object> fetchOrders(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "sort", defaultValue = "asc", required = false) String direction,
			@RequestParam(name = "field", defaultValue = "name", required = false) String property) {
		return this.orderService.fetchAll(page, size, direction, property);
	}

	@GetMapping("/{id}")
	public Order fetchOrderById(@PathVariable("id") long orderId) {
		return this.orderService.findById(orderId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Order saveOrder(@RequestBody @Valid Order order) {
		return this.orderService.save(order);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("id") long orderId) {
		this.orderService.deleteById(orderId);
	}

	@GetMapping("/price")
	public Map<String, Object> fetchOrdersByPriceRange(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "10", required = false) int size,
			@RequestParam(name = "min", defaultValue = "400", required = false) double min,
			@RequestParam(name = "max", defaultValue = "2000", required = false) double max) {
		return this.orderService.findOrdersByPriceRange(page, size, min, max);
	}
	
	@GetMapping("/users")
	public String fetchAllUsers() {
						Mono<String> monoResponse = this.webClient
															.get()
															.uri("/users")
															.retrieve()
												            .bodyToMono(String.class)
												            .timeout(Duration.ofSeconds(5))
												            .retryWhen(
												                    Retry.backoff(4, Duration.ofSeconds(5))
												                            .filter(throwable -> throwable instanceof Exception));
															
								/*.exchangeToMono((res) -> res.bodyToMono(String.class))
								.block();*/
		
								/*
								 * ResponseSpec responseValue = retrieve.onStatus(status ->
								 * status.is2xxSuccessful(), (value) -> { Mono<String> response =
								 * value.bodyToMono(String.class); System.out.println(response); return null;
								 * 
								 * });
								 */
		
								/*
								 * String response = webClient.post() .uri(new
								 * URI("https://jsonplaceholder.typicode.org")) .header("Authorization",
								 * "Bearer MY_SECRET_TOKEN") .contentType(MediaType.APPLICATION_FORM_URLENCODED)
								 * .accept(MediaType.APPLICATION_JSON)
								 * .body(BodyInserters.fromFormData(bodyValues)) .retrieve()
								 * .bodyToMono(String.class) .block();
								 */
						
						
							return monoResponse.block();
	}

	private Object handleError(String reasonPhrase) {
		// TODO Auto-generated method stub
		return null;
	}
}
