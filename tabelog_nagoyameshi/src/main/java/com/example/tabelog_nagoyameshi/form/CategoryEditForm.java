package com.example.tabelog_nagoyameshi.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryEditForm {
	@NotNull
	private Integer id;
	
	@NotNull
	private String categoryName;

}