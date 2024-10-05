package com.sgu.givingsgu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ProjectLikeId.class)
@Table(name = "project_likes")
public class ProjectLike {
    @Id
    @Column(name = "user_id")
    private Long userId;      // Foreign Key referencing Users

    @Id
    @Column(name = "project_id")
    private Long projectId;   // Foreign Key referencing Projects

}