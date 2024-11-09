package com.sgu.givingsgu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rewards")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "points_required", nullable = false)
    private Integer pointsRequired;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;
}