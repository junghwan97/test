package com.example.secondhand.service;

import com.example.secondhand.ItemRepository;
import com.example.secondhand.dto.ItemDeleteResponseDTO;
import com.example.secondhand.dto.ItemRequestDTO;
import com.example.secondhand.dto.ItemResponseDTO;
import com.example.secondhand.dto.ItemSelectResponseDTO;
import com.example.secondhand.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDTO createItem(ItemRequestDTO requestDTO) {
        Item item = new Item(requestDTO);
        Item newItem = itemRepository.save(item);
        return new ItemResponseDTO(newItem);
    }

    public List<ItemSelectResponseDTO> getItems() {
        return itemRepository.findAll().stream().map(ItemSelectResponseDTO::new).toList();
    }

    @Transactional
    public ItemResponseDTO updateItem(Long id, ItemRequestDTO requestDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
        item.update(requestDTO);
        return new ItemResponseDTO(item);
    }

    public ItemDeleteResponseDTO deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 상품입니다."));
        itemRepository.delete(item);

        return new ItemDeleteResponseDTO("삭제완료");

    }
}
