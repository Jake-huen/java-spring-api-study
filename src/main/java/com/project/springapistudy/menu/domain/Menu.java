package com.project.springapistudy.menu.domain;

import com.project.springapistudy.menu.dto.MenuDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menuName;
    private int price;

    @Builder
    public Menu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    public void update(MenuDto.Request request) {
        this.menuName = request.getMenuName();
        this.price = request.getPrice();
    }
}
