package me.donggyeong.producer.service;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.donggyeong.producer.dto.DataRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {
	private final KafkaTemplate<String, DataRequest> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topicName;

	public void sendDataRequest(DataRequest dataRequest) {
		kafkaTemplate.send(topicName, dataRequest)
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
