package com.example.secondhand.controller;

import com.example.secondhand.dto.ItemDeleteResponseDTO;
import com.example.secondhand.dto.ItemRequestDTO;
import com.example.secondhand.dto.ItemResponseDTO;
import com.example.secondhand.dto.ItemSelectResponseDTO;
import com.example.secondhand.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 판매 게시글 작성
    @PostMapping("/post")
    public ItemResponseDTO createItem(@RequestBody ItemRequestDTO requestDTO) {
        return itemService.createItem(requestDTO);
    }

    // 판매 게시글 전체 리스트 조회
    @GetMapping("/post")
    public List<ItemSelectResponseDTO> getItems() {
        return itemService.getItems();
    }

    // 판매 게시글 수정
    @PutMapping("/post/{id}")
    public ItemResponseDTO updateItem(@PathVariable Long id, @RequestBody ItemRequestDTO requestDTO) {
        return itemService.updateItem(id, requestDTO);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ItemDeleteResponseDTO deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
