package com.example.test28_09jpa.Test13;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {
    @Id
    private String id;
    private String title;

    @PrePersist
    private void generateId() {
        if (id == null) {
            // Tạo ID từ thời gian và số ngẫu nhiên
            String timestamp = LocalDateTime.now().toString();
            String random = String.valueOf(new Random().nextInt(1000));
            id = timestamp + random;
        }
    }
}