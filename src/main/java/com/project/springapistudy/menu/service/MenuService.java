package com.project.springapistudy.menu.service;

import com.project.springapistudy.menu.domain.Menu;
import com.project.springapistudy.menu.domain.MenuRepository;
import com.project.springapistudy.menu.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuDto.Response getMenu(String menuName) {
        Optional<Menu> menuEntity = menuRepository.findByMenuName(menuName);
        if (menuEntity.isPresent()) {
            return MenuDto.entityToDto(menuEntity.get());
        }
        throw new RuntimeException("해당하는 메뉴가 존재하지 않습니다.");
    }

    public MenuDto.Response addMenu(MenuDto.Request request) {
        if(menuRepository.findByMenuName(request.getMenuName()).isPresent()){
            throw new RuntimeException("해당하는 메뉴가 이미 존재합니다");
        }
        Menu menuEntity = Menu.builder()
                .menuName(request.getMenuName())
                .price(request.getPrice())
                .build();
        menuRepository.save(menuEntity);
        return MenuDto.entityToDto(menuEntity);
    }

    public List<MenuDto.Response> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(menuEntity -> MenuDto.entityToDto(menuEntity))
                .collect(Collectors.toList());
    }

    public MenuDto.Response editMenu(String menuName, MenuDto.Request request) {
        Optional<Menu> byMenuNameEntity = menuRepository.findByMenuName(menuName);
        if (byMenuNameEntity.isPresent()) {
            byMenuNameEntity.get().update(request);
            menuRepository.save(byMenuNameEntity.get());
            return MenuDto.entityToDto(byMenuNameEntity.get());
        }
        throw new RuntimeException("해당하는 메뉴가 존재하지 않습니다");
    }

    public String deleteMenu(String menuName) {
        Optional<Menu> byMenuNameEntity = menuRepository.findByMenuName(menuName);
        if (byMenuNameEntity.isPresent()) {
            menuRepository.delete(byMenuNameEntity.get());
            return menuName + " 메뉴가 삭제되었습니다";
        }
        throw new RuntimeException("해당하는 메뉴가 존재하지 않습니다");
    }
}
