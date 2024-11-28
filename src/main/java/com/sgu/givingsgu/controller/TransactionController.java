package com.sgu.givingsgu.controller;
import com.sgu.givingsgu.dto.ResponseWrapper;
import com.sgu.givingsgu.dto.TransactionUserDTO;
import com.sgu.givingsgu.helper.ExcelExportHelper;
import com.sgu.givingsgu.model.Transaction;
import com.sgu.givingsgu.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }


    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionService.updateTransaction(id, transactionDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }


    @GetMapping("/project/{projectId}")
    public ResponseWrapper<Page<TransactionUserDTO>> getTransactionsByProjectId(
            @PathVariable Long projectId,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionUserDTO> transactions = transactionService.getTransactionsByProjectId(projectId, pageable);
        return new ResponseWrapper<>(200, "Success", transactions);
    }


    @GetMapping("/export/excel/{projectId}")
    public ResponseEntity<byte[]> exportTransactionsToExcel(  @PathVariable Long projectId) {
        try {
            List<TransactionUserDTO> transactions = transactionService.getTransactionsByProjectId(projectId, PageRequest.of(0, Integer.MAX_VALUE)).getContent();
            ByteArrayOutputStream outputStream = ExcelExportHelper.exportTransactionsToExcel(transactions);

            // Trả về file Excel
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

}