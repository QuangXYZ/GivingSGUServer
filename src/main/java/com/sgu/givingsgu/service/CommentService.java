package com.sgu.givingsgu.service;

import com.sgu.givingsgu.model.Comment;
import com.sgu.givingsgu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null); // Hoặc ném ngoại lệ nếu không tìm thấy
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setUserId(commentDetails.getUserId());
            comment.setProjectId(commentDetails.getProjectId());
            comment.setContent(commentDetails.getContent());
            return commentRepository.save(comment);
        }
        return null; // Hoặc ném ngoại lệ nếu không tìm thấy
    }
}
