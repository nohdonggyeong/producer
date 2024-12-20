package me.donggyeong.producer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.donggyeong.producer.dto.SourceDataResponse;
import me.donggyeong.producer.repository.SourceDataRepository;

@Service
@RequiredArgsConstructor
public class SourceDataServiceImpl implements SourceDataService{
	private final SourceDataRepository sourceDataRepository;

	@Override
	@Transactional(readOnly = true)
	public List<SourceDataResponse> fetchUpTo10AfterOffset(Long offset) {
		return sourceDataRepository.fetchUpTo10AfterOffset(offset).stream().map(SourceDataResponse::new).toList();
	}
}
