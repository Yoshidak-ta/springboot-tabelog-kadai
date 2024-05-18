package com.example.tabelog_nagoyameshi.form;

import com.example.tabelog_nagoyameshi.entity.Store;
import com.example.tabelog_nagoyameshi.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewEditForm {
	@NotNull
	private Integer id;
	
	@NotNull
	private User user;
	
	@NotNull
	private Store store;
	
	@NotNull(message = "評価を選択してください。")
	private String stars;
	
	@NotNull(message = "コメントを入力してください。")
	private String reviewComment;
	

}