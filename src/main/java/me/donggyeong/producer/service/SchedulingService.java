package me.donggyeong.producer.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.donggyeong.producer.dto.DataRequest;
import me.donggyeong.producer.enums.Action;

@Service
@RequiredArgsConstructor
public class SchedulingService {
	private final KafkaProducerService kafkaProducerService;

	@Scheduled(cron = "0/10 * * * * ?")
	public void scheduleSendingDataRequest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.KOREA);
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		String formattedDate = now.format(formatter);

		long timestamp = ZonedDateTime.now().toInstant().toEpochMilli();

		Action action = null;
		String category = null;

		Random random = new Random();
		switch (random.nextInt(4)) {
			case 0:
				action = Action.INDEX;
				break;
			case 1:
				action = Action.CREATE;
				break;
			case 2:
				action = Action.UPDATE;
				break;
			case 3:
				action = Action.DELETE;
				break;
			default:
				break;
		}

		random = new Random();
		switch (random.nextInt(4)) {
			case 0:
				category = "Application";
				break;
			case 1:
				category = "Asset";
				break;
			case 2:
				category = "Connector";
				break;
			case 3:
				category = "Knowledge";
				break;
			default:
				break;
		}

		Map<String, Object> document = new HashMap<>();
		document.put("tenant", "samsungsds");
		document.put("id", timestamp);
		document.put("category", category);
		document.put("title", "test title " + timestamp);
		document.put("description", "test description " + timestamp);
		document.put("creator", "Noh Donggyeong");
		document.put("updater", "Noh Donggyeong");
		document.put("createdAt", formattedDate);
		document.put("updatedAt", formattedDate);
		DataRequest dataRequest = new DataRequest(action, document);
		kafkaProducerService.sendDataRequest(dataRequest);
	}
}
