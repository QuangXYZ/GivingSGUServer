package com.sgu.givingsgu.dto;

import com.sgu.givingsgu.model.Project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectResponse {
    private Project project;
    private boolean isLiked;
    private int likeCount;
}
