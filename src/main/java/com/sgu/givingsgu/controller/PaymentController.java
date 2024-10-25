package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.dto.PaymentRequest;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.model.Transaction;
import com.sgu.givingsgu.service.DonationService;
import com.sgu.givingsgu.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {
        Donation donation = donationService.findByUserIdAndProjectId(request.getUserId(), request.getProjectId());
        if (donation == null) {
            donation = new Donation();
            donation.setProjectId(request.getProjectId());
            donation.setUserId(request.getUserId());
            donation.setDonateDate(new Date());
            donationService.createDonation(donation);
        }

        Transaction transaction = new Transaction();
        transaction.setDonation(donation);
        transaction.setTransactionDate(new Date());
        transaction.setPaymentMethod(request.getPaymentMethod());
        transaction.setStatus("SUCCESS");
        transactionService.saveTransaction(transaction);

        return ResponseEntity.ok("Transaction processed successfully.");
    }
}
