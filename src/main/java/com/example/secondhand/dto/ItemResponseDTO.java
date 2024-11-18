package com.example.secondhand.dto;


import com.example.secondhand.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponseDTO {
    private Long id;
    private String username;
    private String title;
    private String content;
    private int price;

    public ItemResponseDTO(Item item) {
        this.id = item.getId();
        this.username = item.getUsername();
        this.title = item.getTitle();
        this.content = item.getContent();
        this.price = item.getPrice();
    }
}
