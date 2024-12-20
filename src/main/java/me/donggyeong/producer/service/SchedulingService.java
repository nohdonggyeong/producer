package me.donggyeong.producer.service;

import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import me.donggyeong.producer.dto.ItemRequest;
import me.donggyeong.producer.dto.SourceDataResponse;
import me.donggyeong.producer.enums.Action;

@Service
@RequiredArgsConstructor
public class SchedulingService {
	private final SourceDataService sourceDataService;
	private final KafkaProducerService kafkaProducerService;
	private final ObjectMapper objectMapper;

	private Long offsetId;

	@Scheduled(cron = "0/10 * * * * ?")
	public void scheduleSendingDataRequest() {
		if (offsetId == null) {
			offsetId = 0L;
		}

		List<SourceDataResponse> sourceDataResponseList = sourceDataService.fetchUpTo10AfterOffset(offsetId);
		for (SourceDataResponse sourceDataResponse : sourceDataResponseList) {
			Map<String, Object> map = objectMapper.convertValue(sourceDataResponse, Map.class);
			ItemRequest itemRequest = new ItemRequest(Action.INDEX, "movie-evaluation", sourceDataResponse.getId().toString(), map);
			kafkaProducerService.sendDataRequest(itemRequest);
			offsetId = sourceDataResponse.getId();
		}
	}


	// v1 scheduleSendingDataRequest method
	/*
	private static long count = 0;

	@Scheduled(cron = "0/2 * * * * ?")
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

		Map<String, Object> data = new HashMap<>();
		data.put("source", "hub");
		if (!Action.DELETE.equals(action)) {
			count++;
		}
		data.put("dataId", count);
		data.put("category", category);
		data.put("title", "test title " + timestamp);
		data.put("description", "test description " + timestamp);
		data.put("creator", "Noh Donggyeong");
		data.put("updater", "Noh Donggyeong");
		data.put("createdAt", formattedDate);
		data.put("updatedAt", formattedDate);
		ItemRequest itemRequest = new ItemRequest(action, "hub", "id-" + count, data);
		kafkaProducerService.sendDataRequest(itemRequest);
	}
	*/
}
