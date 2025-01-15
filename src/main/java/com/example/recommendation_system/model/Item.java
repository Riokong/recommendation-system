package com.example.recommendation.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();
}