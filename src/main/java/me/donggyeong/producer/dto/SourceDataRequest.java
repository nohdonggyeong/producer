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
	@NotNull(message = "'source' is required.")
	private String source;
	@NotNull(message = "'dataId' is required.")
	private Long dataId;
	@NotNull(message = "'data' is required.")
	private Map<String, Object> data;
}