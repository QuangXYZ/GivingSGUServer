package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.dto.LoginResponse;
import com.sgu.givingsgu.dto.ProjectResponse;
import com.sgu.givingsgu.dto.ResponseWrapper;
import com.sgu.givingsgu.model.Project;
import com.sgu.givingsgu.model.User;
import com.sgu.givingsgu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @GetMapping
    public ResponseEntity<ResponseWrapper<List<ProjectResponse>>> getAllProjects(@RequestParam(required = false) Long userId ) {
        try {
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Successful", projectService.getProjectsForUser(userId)));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "Internal Server Error", null));
        }
    }



    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> partialUpdateProject(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Tìm project theo ID và xử lý Optional
        Optional<Project> optionalProject = projectService.getProjectById(id);

        // Nếu project không tồn tại, trả về 404
        if (!optionalProject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Project existingProject = optionalProject.get(); // Lấy project từ Optional

        // Duyệt qua các trường và cập nhật tương ứng
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingProject.setName((String) value);
                    break;
                case "description":
                    existingProject.setDescription((String) value);
                    break;
                case "startDate":
                    existingProject.setStartDate((Date) value);
                    break;
                case "endDate":
                    existingProject.setEndDate((Date) value);
                    break;
                case "targetAmount":
                    existingProject.setTargetAmount((Double) value);
                    break;
                case "currentAmount":
                    existingProject.setCurrentAmount((Double) value);
                    break;
                case "status":
                    existingProject.setStatus((String) value);
                    break;
                case "numberDonors":
                    existingProject.setNumberDonors((Integer) value);
                    break;
                case "imageUrls":
                    existingProject.setImageUrls((String) value);
                    break;
                default:
                    // Không làm gì nếu trường không khớp
                    break;
            }
        });

        // Lưu project sau khi cập nhật
        Project updatedProject = projectService.saveProject(existingProject);
        return ResponseEntity.ok(updatedProject);
    }


    @PostMapping("/{projectId}/like")
    public ResponseEntity<ResponseWrapper<String>> likeProject(@PathVariable Long projectId, @RequestParam Long userId) {
        try {
            projectService.likeProject(projectId, userId);
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Project liked successfully", "Liked"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Failed to like project", null));
        }
    }

    @DeleteMapping("/{projectId}/like")
    public ResponseEntity<ResponseWrapper<String>> unlikeProject(@PathVariable Long projectId, @RequestParam Long userId) {
        try {
            projectService.unlikeProject(projectId, userId);
            return ResponseEntity.ok(new ResponseWrapper<>(200, "Project unliked successfully", "Unliked"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Failed to unlike project", null));
        }
    }
}
