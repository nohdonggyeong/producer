package me.donggyeong.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void create() {
		kafkaTemplate.send("topic", "say hello~");
	}
}
