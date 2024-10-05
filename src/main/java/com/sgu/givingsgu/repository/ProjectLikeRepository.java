package com.sgu.givingsgu.repository;

import java.util.List;
import com.sgu.givingsgu.model.ProjectLike;
import com.sgu.givingsgu.model.ProjectLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLike, ProjectLikeId> {
    // Tìm tất cả các người dùng đã thích một dự án cụ thể
    List<ProjectLike> findByProjectId(Long projectId);

    boolean existsByUserIdAndProjectId(Long userId, Long projectId); // Kiểm tra xem một user đã thích một project chưa

}
