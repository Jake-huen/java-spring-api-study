package com.project.springapistudy.menu.dto;

import com.project.springapistudy.menu.domain.Menu;
import lombok.Builder;
import lombok.Getter;

public class MenuDto {

    @Getter
    @Builder
    public static class Request {
        private String menuName;
        private int price;
    }

    @Getter
    @Builder
    public static class Response {
        private String menuName;
        private int price;
    }

    public static MenuDto.Response entityToDto(Menu menuEntity) {
        return MenuDto.Response.builder()
                .menuName(menuEntity.getMenuName())
                .price(menuEntity.getPrice())
                .build();
    }
}
