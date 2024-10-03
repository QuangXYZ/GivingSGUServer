package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
