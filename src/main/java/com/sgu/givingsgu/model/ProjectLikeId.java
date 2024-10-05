package com.sgu.givingsgu.model;
import java.io.Serializable;
import java.util.Objects;


public class ProjectLikeId implements Serializable {
    private Long userId;
    private Long projectId;

    // Default constructor
    public ProjectLikeId() {}

    // Constructor with parameters
    public ProjectLikeId(Long userId, Long projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    // Override equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectLikeId)) return false;
        ProjectLikeId that = (ProjectLikeId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, projectId);
    }
}
