package com.sgu.givingsgu.service;

import com.sgu.givingsgu.dto.TransactionUserDTO;
import com.sgu.givingsgu.model.Transaction;
import com.sgu.givingsgu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null); // Hoặc ném ngoại lệ nếu không tìm thấy
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction != null) {
            transaction.setDonation(transactionDetails.getDonation());
            transaction.setTransactionDate(transactionDetails.getTransactionDate());
            transaction.setPaymentMethod(transactionDetails.getPaymentMethod());
            transaction.setStatus(transactionDetails.getStatus());
            return transactionRepository.save(transaction);
        }
        return null; // Hoặc ném ngoại lệ nếu không tìm thấy
    }



    public Page<TransactionUserDTO> getTransactionsByProjectId(Long projectId, Pageable pageable) {
        return new PageImpl<>(
                transactionRepository.findTransactionsByProjectId(projectId, pageable),
                pageable,
                transactionRepository.count() // Số lượng bản ghi
        );
    }
}
