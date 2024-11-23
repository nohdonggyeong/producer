package me.donggyeong.producer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerServiceTest {
	@Autowired
	private ProducerService producerService;

	@Test
	void create() {
		producerService.create();
	}
}