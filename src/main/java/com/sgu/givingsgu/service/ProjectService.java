package com.sgu.givingsgu.service;

import com.sgu.givingsgu.dto.ProjectResponse;
import com.sgu.givingsgu.model.Project;
import com.sgu.givingsgu.model.ProjectLike;
import com.sgu.givingsgu.model.ProjectLikeId;
import com.sgu.givingsgu.repository.ProjectLikeRepository;
import com.sgu.givingsgu.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectLikeRepository projectLikeRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }


    public List<ProjectResponse> getProjectsForUser(Long userId) {
        List<Project> projects = projectRepository.findAll();

        return projects.stream()
                .map(project -> new ProjectResponse(
                        project,
                        userId != null && projectLikeRepository.existsByUserIdAndProjectId(userId, project.getProjectId()),
                        projectLikeRepository.countByProjectId(project.getProjectId()) // Tính số lượng like cho mỗi dự án
                ))
                .collect(Collectors.toList());
    }


    public void likeProject(Long projectId, Long userId) {
        // Kiểm tra xem người dùng đã like dự án hay chưa
        if (!projectLikeRepository.existsByUserIdAndProjectId(userId, projectId)) {
            ProjectLike projectLike = new ProjectLike(userId, projectId);
            projectLikeRepository.save(projectLike);
        }
    }

    public void unlikeProject(Long projectId, Long userId) {
        // Tìm kiếm để xóa
        Optional<ProjectLike> projectLike = projectLikeRepository.findById(new ProjectLikeId(userId, projectId));
        projectLike.ifPresent(projectLikeRepository::delete);
    }


    public Project findById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
    }

    // Lưu hoặc cập nhật dự án
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }
}
