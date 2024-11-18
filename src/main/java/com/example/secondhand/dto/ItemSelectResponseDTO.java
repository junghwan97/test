package com.example.secondhand.dto;


import com.example.secondhand.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemSelectResponseDTO {
    private Long id;
    private String title;
    private String username;
    private int price;

    public ItemSelectResponseDTO(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.username = item.getUsername();
        this.price = item.getPrice();
    }
}
