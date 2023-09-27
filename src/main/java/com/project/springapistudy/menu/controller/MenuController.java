package com.project.springapistudy.menu.controller;

import com.project.springapistudy.menu.dto.MenuDto;
import com.project.springapistudy.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping()
    public MenuDto.Response getMenu(@RequestParam String menuName){
        return menuService.getMenu(menuName);
    }

    @PostMapping()
    public MenuDto.Response addMenu(@RequestBody MenuDto.Request request) {
        return menuService.addMenu(request);
    }

    @GetMapping("/all")
    public List<MenuDto.Response> getAllBullets(){
        return menuService.getAllMenus();
    }

    @PostMapping("/edit")
    public MenuDto.Response editBullet(@RequestParam String menuName, @RequestBody MenuDto.Request request){
        return menuService.editMenu(menuName, request);
    }

    @PostMapping("/delete")
    public String deleteBullet(@RequestParam String menuName) {
        return menuService.deleteMenu(menuName);
    }
}
