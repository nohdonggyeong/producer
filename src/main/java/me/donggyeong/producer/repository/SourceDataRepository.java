package me.donggyeong.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.donggyeong.producer.entity.SourceData;

@Repository
public interface SourceDataRepository extends JpaRepository<SourceData, Long>, SourceDataRepositoryCustom {
}
