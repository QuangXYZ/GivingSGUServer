package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.model.Project;
import com.sgu.givingsgu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
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
}
