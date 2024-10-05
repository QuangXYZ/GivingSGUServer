package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.model.Comment;
import com.sgu.givingsgu.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
