package me.donggyeong.producer.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "source_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SourceData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "score", nullable = false)
	private Integer score;

	@Column(name = "author", nullable = false)
	private String author;

	@Column(name = "evaluation", nullable = false)
	private String evaluation;

	@Column(name = "evaluated_at", nullable = false)
	private ZonedDateTime evaluatedAt;
}
