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
public class DataRequest {
	@NotNull(message = "'action' is required.")
	private Action action;
	@NotNull(message = "'document' is required.")
	private Map<String, Object> document;
}