package com.project.springapistudy.menu.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MenuRequestDto {
    private String menuName;
    private int price;
}
