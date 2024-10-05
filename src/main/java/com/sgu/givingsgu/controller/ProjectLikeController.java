package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.model.ProjectLike;
import com.sgu.givingsgu.service.ProjectLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-likes")
public class ProjectLikeController {

    @Autowired
    private ProjectLikeService projectLikeService;

    @GetMapping
    public List<ProjectLike> getAllProjectLikes() {
        return projectLikeService.getAllProjectLikes();
    }

    @GetMapping("/{userId}/{projectId}")
    public ProjectLike getProjectLike(@PathVariable Long userId, @PathVariable Long projectId) {
        return projectLikeService.getProjectLike(userId, projectId);
    }

    @PostMapping
    public ProjectLike createProjectLike(@RequestBody ProjectLike projectLike) {
        return projectLikeService.saveProjectLike(projectLike);
    }

    @DeleteMapping("/{userId}/{projectId}")
    public void deleteProjectLike(@PathVariable Long userId, @PathVariable Long projectId) {
        projectLikeService.deleteProjectLike(userId, projectId);
    }
}
