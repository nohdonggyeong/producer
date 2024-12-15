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
	@NotNull(message = "'target' is required.")
	private String target;
	@NotNull(message = "'docId' is required.")
	private String docId;
	@NotNull(message = "'docBody' is required.")
	private Map<String, Object> docBody;
}