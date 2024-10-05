package com.sgu.givingsgu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    @Column(name = "user_id", nullable = false)
    private Long userId;      // Foreign Key referencing Users

    @Column(name = "project_id", nullable = false)
    private Long projectId;   // Foreign Key referencing Project

    @Column(nullable = false)
    private String content;
}
