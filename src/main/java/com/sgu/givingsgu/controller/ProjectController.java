package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.model.Projects;
import com.sgu.givingsgu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Projects> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public Projects createProject(@RequestBody Projects project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/{id}")
    public Optional<Projects> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    @GetMapping("/organization/{organizationId}")
    public List<Projects> getProjectsByOrganizationId(@PathVariable Long organizationId) {
        return projectService.getProjectsByOrganizationId(organizationId);
    }
}
