package me.donggyeong.producer.service;

import java.util.List;

import me.donggyeong.producer.dto.SourceDataResponse;

public interface SourceDataService {
	List<SourceDataResponse> fetchUpTo10AfterOffset(Long offset);
}
