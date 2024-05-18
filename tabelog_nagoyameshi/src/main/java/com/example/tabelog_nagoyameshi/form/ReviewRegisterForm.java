package com.example.tabelog_nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	@NotBlank(message = "評価を選択してください。")
	private String stars;
	
	@NotBlank(message = "コメントを入力してください。")
	private String reviewComment;
	
}