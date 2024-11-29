package com.sgu.givingsgu.repository;

import com.sgu.givingsgu.dto.TopDonorDTO;
import com.sgu.givingsgu.dto.TransactionUserDTO;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.sgu.givingsgu.dto.TopDonorDTO(u.userId, u.fullName, u.imageUrl, SUM(t.amount),  d.donateDate) " +
            "FROM Transaction t " +
            "JOIN t.donation d " +
            "JOIN User u ON d.userId = u.userId " +
            "GROUP BY u.userId, u.fullName, u.imageUrl " +
            "ORDER BY SUM(t.amount) DESC")
    List<TopDonorDTO> findTop10Donors(Pageable pageable);


    @Query("SELECT new com.sgu.givingsgu.dto.TopDonorDTO(u.userId, u.fullName, u.imageUrl, SUM(t.amount), d.donateDate) " +
            "FROM Transaction t " +
            "JOIN t.donation d " +
            "JOIN User u ON d.userId = u.userId " +
            "WHERE d.projectId = :projectId " +
            "GROUP BY u.userId, u.fullName, u.imageUrl, d.donateDate " +
            "ORDER BY SUM(t.amount) DESC")
    List<TopDonorDTO> findTop10DonorsByProjectId(@Param("projectId") Long projectId, Pageable pageable);






    @Query("SELECT new com.sgu.givingsgu.dto.TransactionUserDTO(" +
            "t.id, t.amount, t.transactionDate, t.paymentMethod, t.status, t.token, t.transactionId, " +
            "u.fullName, f.name, u.studentId) " +
            "FROM Transaction t " +
            "JOIN t.donation d " +
            "JOIN User u ON d.userId = u.userId " +
            "JOIN Faculty f ON u.faculty.facultyId = f.facultyId " +
            "WHERE d.projectId = :projectId " +
            "ORDER BY t.transactionDate DESC")
    List<TransactionUserDTO> findTransactionsByProjectId(@Param("projectId") Long projectId, Pageable pageable);

    @Query("SELECT COUNT(t) " +
            "FROM Transaction t " +
            "JOIN t.donation d " +
            "WHERE d.projectId = :projectId")
    long countTransactionsByProjectId(@Param("projectId") Long projectId);
}



