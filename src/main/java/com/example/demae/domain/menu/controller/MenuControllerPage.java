package com.example.demae.domain.menu.controller;

import com.example.demae.domain.menu.dto.response.MenuResponseDto;
import com.example.demae.domain.menu.service.MenuService;
import com.example.demae.domain.user.entity.User;
import com.example.demae.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/menus")
public class MenuControllerPage {

    private final MenuService menuService;
    private final UserService userService;

    @GetMapping
    public String findMenus(@PathVariable Long storeId,
                            @AuthenticationPrincipal UserDetails userDetails,
                            Model model){
        if(userDetails == null){
            return "root/error";
        }
        List<MenuResponseDto> findMenus = menuService.findMenus(storeId);
        model.addAttribute("menuList", findMenus);
        User user = userService.findUser(userDetails.getUsername());
        model.addAttribute("storeId", storeId);
        model.addAttribute("user", user);
//        if (user.getUserRole().name().equals("STORE") && user.getStore().getStoreId().equals(storeId)) {
//            return "menu/showMenuPage";
//        }
//        return "menu/showMenuPageUser";
        return "menu/showMenuPage";
    }

    @GetMapping("/{menuId}")
    public String findMenu(@PathVariable Long storeId,
                           @PathVariable Long menuId,
                           @AuthenticationPrincipal UserDetails userDetails,
                           Model model){

        MenuResponseDto findMenu = menuService.findMenuStore(storeId,menuId);
        model.addAttribute("menuOne", findMenu);
        if(userDetails == null){
            return "root/error";
        }
        User user = userService.findUser(userDetails.getUsername());
//        if (user.getUserRole().name().equals("STORE") && user.getStore().getStoreId().equals(storeId)) {
//            return "menu/showSelectMenu";
//        }
//        return "menu/showSelectMenuUser";
        return "menu/showSelectMenu";
    }

    @GetMapping("/join")
    public String menuCreatePage(@PathVariable Long storeId,
                                 Model model){
        model.addAttribute("storeId", storeId);
        return "menu/menu";
    }
}
