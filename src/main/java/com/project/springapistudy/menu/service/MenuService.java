package com.project.springapistudy.menu.service;

import com.project.springapistudy.menu.domain.Menu;
import com.project.springapistudy.menu.domain.MenuRepository;
import com.project.springapistudy.menu.dto.MenuDto;
import com.project.springapistudy.menu.dto.MenuResponseDto;
import com.project.springapistudy.menu.exception.ApiException;
import com.project.springapistudy.menu.exception.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuResponseDto getMenu(String menuName) throws ApiException {
        Menu menuEntity = menuRepository.findByMenuName(menuName).orElseThrow(()->
                new ApiException(Constants.ExceptionClass.MENU, HttpStatus.BAD_REQUEST, "해당하는 메뉴가 존재하지 않습니다"));
        return MenuResponseDto.entityToDto(menuEntity);
    }

    public MenuResponseDto addMenu(MenuDto.Request request) throws ApiException {
        Optional<Menu> byMenuName = menuRepository.findByMenuName(request.getMenuName());
        if (byMenuName.isPresent()) {
            throw new ApiException(Constants.ExceptionClass.MENU, HttpStatus.BAD_REQUEST, "해당하는 메뉴가 이미 존재합니다");
        }
        Menu menuEntity = Menu.builder()
                .menuName(request.getMenuName())
                .price(request.getPrice())
                .build();
        menuRepository.save(menuEntity);
        return MenuResponseDto.entityToDto(menuEntity);
    }

    public List<MenuResponseDto> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(MenuResponseDto::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MenuResponseDto editMenu(String menuName, MenuDto.Request request) throws ApiException {
        Menu byMenuNameEntity = menuRepository.findByMenuName(menuName).orElseThrow(() ->
                new ApiException(Constants.ExceptionClass.MENU, HttpStatus.BAD_REQUEST, "해당하는 메뉴가 존재하지 않습니다"));
        byMenuNameEntity.update(request);
        return MenuResponseDto.entityToDto(byMenuNameEntity);

    }

    public String deleteMenu(String menuName) throws ApiException {
        Menu byMenuNameEntity = menuRepository.findByMenuName(menuName).orElseThrow(()->
                new ApiException(Constants.ExceptionClass.MENU, HttpStatus.BAD_REQUEST, "해당하는 메뉴가 존재하지 않습니다"));
        menuRepository.delete(byMenuNameEntity);
        return menuName + " 메뉴가 삭제되었습니다";
    }
}
