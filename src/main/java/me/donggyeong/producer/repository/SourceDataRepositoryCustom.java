package me.donggyeong.producer.repository;

import java.util.List;

import me.donggyeong.producer.entity.SourceData;

public interface SourceDataRepositoryCustom {
	List<SourceData> fetchUpTo10AfterOffset(Long offset);
}
