package com.example.demae.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequestDto {

	@NotBlank
	private String storeName;

	@NotBlank
	private String storeAddress;

	@NotBlank
	private String storeCategory;
}
