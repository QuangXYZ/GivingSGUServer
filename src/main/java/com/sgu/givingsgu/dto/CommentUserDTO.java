package com.sgu.givingsgu.dto;


import lombok.Data;

@Data
public class CommentUserDTO {
    private Long commentId;
    private Long projectId;
    private String content;
    private Long userId;
    private String username;
    private String fullName;
    private String imageUrl;
    private String email;

    // Constructor
    public CommentUserDTO(Long commentId, Long projectId, String content, Long userId, String username, String fullName,String imageUrl, String email) {
        this.commentId = commentId;
        this.projectId = projectId;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.email = email;
    }

    // Getters and Setters
}
