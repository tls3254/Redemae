package com.example.demae.domain.store.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreModifyRequestDto {

    private String storeName;
    private String storeAddress;
    private String storeCategory;
}
