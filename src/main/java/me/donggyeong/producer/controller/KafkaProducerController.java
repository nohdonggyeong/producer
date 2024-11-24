package me.donggyeong.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.donggyeong.producer.dto.DataRequest;
import me.donggyeong.producer.service.KafkaProducerService;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class KafkaProducerController {
	private final KafkaProducerService kafkaProducerService;

	@PostMapping
	public ResponseEntity<String> sendDataRequest(@Valid @RequestBody DataRequest dataRequest) {
		try {
			kafkaProducerService.sendDataRequest(dataRequest);
			return ResponseEntity.accepted().body("Message sending in progress");
		} catch (Exception ex) {
			return ResponseEntity.internalServerError().body("Failed to send message: " + ex.getMessage());
		}
	}
}
