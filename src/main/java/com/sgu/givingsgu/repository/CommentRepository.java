package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Comment;
import com.sgu.givingsgu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProjectId(Long projectId);
}
