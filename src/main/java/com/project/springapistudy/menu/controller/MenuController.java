package com.project.springapistudy.menu.controller;

import com.project.springapistudy.menu.dto.MenuDto;
import com.project.springapistudy.menu.dto.MenuResponseDto;
import com.project.springapistudy.menu.exception.ApiException;
import com.project.springapistudy.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public MenuResponseDto getMenu(@RequestParam String menuName) throws ApiException {
        return menuService.getMenu(menuName);
    }

    @PostMapping
    public MenuResponseDto addMenu(@RequestBody MenuDto.Request request) throws ApiException {
        return menuService.addMenu(request);
    }

    @GetMapping("/all")
    public List<MenuResponseDto> getAllBullets(){
        return menuService.getAllMenus();
    }

    @PutMapping
    public MenuResponseDto editBullet(@RequestParam String menuName, @RequestBody MenuDto.Request request) throws ApiException {
        return menuService.editMenu(menuName, request);
    }

    @DeleteMapping
    public String deleteBullet(@RequestParam String menuName) throws ApiException {
        return menuService.deleteMenu(menuName);
    }
}
