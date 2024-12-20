package me.donggyeong.producer.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.donggyeong.producer.dto.ItemRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
	private final KafkaTemplate<String, ItemRequest> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topicName;

	public void sendDataRequest(ItemRequest itemRequest) {
		kafkaTemplate.send(topicName, itemRequest)
			.whenComplete((result, ex) -> {
				if (ex == null) {
					RecordMetadata metadata = result.getRecordMetadata();
					log.info("Message sent successfully - Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
						metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
				} else {
					log.error("Failed to send message", ex);
				}
			});
	}
}
