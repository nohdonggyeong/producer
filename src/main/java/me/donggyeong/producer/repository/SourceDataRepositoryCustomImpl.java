package me.donggyeong.producer.repository;

import static me.donggyeong.producer.entity.QSourceData.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import me.donggyeong.producer.entity.SourceData;

@Repository
@RequiredArgsConstructor
public class SourceDataRepositoryCustomImpl implements SourceDataRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<SourceData> fetchUpTo10AfterOffset(Long offset) {
		return jpaQueryFactory
			.selectFrom(sourceData)
			.where(sourceData.id.gt(offset))
			.limit(10)
			.orderBy(sourceData.id.asc())
			.fetch();
	}
}
