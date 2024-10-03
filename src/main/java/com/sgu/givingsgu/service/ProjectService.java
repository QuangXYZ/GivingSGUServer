package com.sgu.givingsgu.service;

import com.sgu.givingsgu.model.Projects;
import com.sgu.givingsgu.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Projects> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Projects saveProject(Projects project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }


}
