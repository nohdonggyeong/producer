package me.donggyeong.producer.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.donggyeong.producer.enums.Action;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SourceDataRequest {
	@NotNull(message = "'action' is required.")
	private Action action;
	@NotNull(message = "'targetName' is required.")
	private String targetName;
	@NotNull(message = "'documentId' is required.")
	private Long documentId;
	@NotNull(message = "'documentBody' is required.")
	private Map<String, Object> documentBody;
}