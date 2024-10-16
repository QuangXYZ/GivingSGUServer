package com.sgu.givingsgu.service;

import com.sgu.givingsgu.dto.CommentUserDTO;
import com.sgu.givingsgu.model.Comment;
import com.sgu.givingsgu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            comment.setProjectId(commentDetails.getProjectId());
            comment.setContent(commentDetails.getContent());
            return commentRepository.save(comment);
        }
        return null; // Hoặc ném ngoại lệ nếu không tìm thấy
    }

    public List<CommentUserDTO> getCommentsByProjectId(Long projectId) {
        List<Comment> comments = commentRepository.findByProjectId(projectId);

        // Convert Comment entities to CommentResponseDTOs
        return comments.stream()
                .map(comment -> new CommentUserDTO(
                        comment.getCommentId(),
                        comment.getProjectId(),
                        comment.getContent(),
                        comment.getUser().getUserId(),
                        comment.getUser().getUsername(),
                        comment.getUser().getFullName(),
                        comment.getUser().getImageUrl(),
                        comment.getUser().getEmail()))

                .collect(Collectors.toList());
    }
}
