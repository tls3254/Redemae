package com.example.demae.domain.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuModifyRequestDto {
    private String menuName;
    private int menuPrice;
}
