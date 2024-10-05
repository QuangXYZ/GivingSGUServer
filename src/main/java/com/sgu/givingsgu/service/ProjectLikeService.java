package com.sgu.givingsgu.service;

import com.sgu.givingsgu.model.ProjectLike;
import com.sgu.givingsgu.model.ProjectLikeId;
import com.sgu.givingsgu.repository.ProjectLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectLikeService {

    @Autowired
    private ProjectLikeRepository projectLikeRepository;

    public List<ProjectLike> getAllProjectLikes() {
        return projectLikeRepository.findAll();
    }

    public ProjectLike getProjectLike(Long userId, Long projectId) {
        return projectLikeRepository.findById(new ProjectLikeId(userId, projectId)).orElse(null);
    }

    public ProjectLike saveProjectLike(ProjectLike projectLike) {
        return projectLikeRepository.save(projectLike);
    }

    public void deleteProjectLike(Long userId, Long projectId) {
        projectLikeRepository.deleteById(new ProjectLikeId(userId, projectId));
    }

    public boolean existsByUserIdAndProjectId(Long userId, Long projectId) {
        return projectLikeRepository.existsByUserIdAndProjectId(userId, projectId);
    }
}
