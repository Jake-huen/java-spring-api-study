package com.project.springapistudy.menu.dto;

import com.project.springapistudy.menu.domain.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuResponseDto {

    private String menuName;
    private int price;

    public static MenuResponseDto entityToDto(Menu menuEntity) {
        return MenuResponseDto.builder()
                .menuName(menuEntity.getMenuName())
                .price(menuEntity.getPrice())
                .build();
    }
}
