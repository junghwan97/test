package com.example.secondhand.entity;

import com.example.secondhand.dto.ItemRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item")
@NoArgsConstructor
public class Item extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "username", nullable = false)
    private String username;

    public Item(ItemRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.price = requestDTO.getPrice();
        this.content = requestDTO.getContent();
        this.title = requestDTO.getTitle();
    }

    public void update(ItemRequestDTO requestDTO) {
        this.username = requestDTO.getUsername();
        this.price = requestDTO.getPrice();
        this.content = requestDTO.getContent();
        this.title = requestDTO.getTitle();
    }
}
