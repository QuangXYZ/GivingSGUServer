package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.dto.TopDonorDTO;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.sgu.givingsgu.dto.TopDonorDTO(u.userId, u.fullName, u.imageUrl, SUM(t.amount)) " +
            "FROM Transaction t " +
            "JOIN t.donation d " +
            "JOIN User u ON d.userId = u.userId " +
            "GROUP BY u.userId, u.fullName, u.imageUrl " +
            "ORDER BY SUM(t.amount) DESC")
    List<TopDonorDTO> findTop10Donors(Pageable pageable);
}
