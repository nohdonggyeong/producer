package me.donggyeong.producer.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.donggyeong.producer.entity.SourceData;

@AllArgsConstructor
@Getter
public class SourceDataResponse {
	private Long id;
	private Integer score;
	private String author;
	private String evaluation;
	private ZonedDateTime evaluatedAt;

	public SourceDataResponse(SourceData sourceData) {
		this.id = sourceData.getId();
		this.score = sourceData.getScore();
		this.author = sourceData.getAuthor();
		this.evaluation = sourceData.getEvaluation();
		this.evaluatedAt = sourceData.getEvaluatedAt();
	}
}
