package com.example.demae.domain.menu.controller;

import com.example.demae.domain.menu.dto.request.MenuModifyRequestDto;
import com.example.demae.domain.menu.dto.request.MenuRequestDto;
import com.example.demae.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MenuController {

	private final MenuService menuService;

    @PostMapping("/{storeId}/menus")
    public ResponseEntity<String> createMenu(@PathVariable Long storeId,
                                             @RequestBody MenuRequestDto menuRequestDto,
                                             @AuthenticationPrincipal UserDetails userDetails){
        menuService.createMenu(menuRequestDto, storeId, userDetails.getUsername());
        return ResponseEntity.ok("ok");
    }

    @PatchMapping("/{storeId}/patchMenu/{menuId}")
    public ResponseEntity<String> modifyMenu(@PathVariable Long storeId,
                                            @PathVariable Long menuId,
                                            @RequestBody MenuModifyRequestDto menuRequestDto,
                                            @AuthenticationPrincipal UserDetails userDetails){
        menuService.modifyMenu(storeId,menuId,menuRequestDto,userDetails.getUsername());
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping("/{storeId}/deleteMenu/{menuId}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long storeId,
                                             @PathVariable Long menuId,
                                             @AuthenticationPrincipal UserDetails userDetails){
        menuService.deleteMenu(storeId,menuId,userDetails.getUsername());
        return ResponseEntity.ok("ok");
    }
}
