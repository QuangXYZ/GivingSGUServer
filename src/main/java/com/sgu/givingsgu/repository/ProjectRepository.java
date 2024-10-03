package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    List<Projects> findByOrganizationId(Long organizationId);  // Tìm các dự án theo organizationId
}
