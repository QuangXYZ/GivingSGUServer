package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.dto.PaymentRequest;
import com.sgu.givingsgu.dto.ResponseWrapper;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.model.Project;
import com.sgu.givingsgu.model.Transaction;
import com.sgu.givingsgu.service.DonationService;
import com.sgu.givingsgu.service.ProjectService;
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

    @Autowired
    private ProjectService projectService;

    @PostMapping("/process")
    public ResponseEntity<ResponseWrapper<Transaction>> processPayment(@RequestBody PaymentRequest request) {
        Donation donation = donationService.findByUserIdAndProjectId(request.getUserId(), request.getProjectId());
        if (donation == null) {
            donation = new Donation();
            donation.setProjectId(request.getProjectId());
            donation.setUserId(request.getUserId());
            donation.setDonateDate(new Date());
            donationService.createDonation(donation);

            Project project = projectService.findById(request.getProjectId());
            project.setNumberDonors(project.getNumberDonors() + 1);
            projectService.saveProject(project);
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionId(request.getTransactionId());
        transaction.setDonation(donation);
        transaction.setTransactionDate(new Date());
        transaction.setPaymentMethod(request.getPaymentMethod());
        transaction.setStatus("SUCCESS");
        transaction.setToken(request.getToken());

        Project project = projectService.findById(request.getProjectId());
        project.setCurrentAmount((project.getCurrentAmount() == null ? 0 : project.getCurrentAmount()) + request.getAmount());
        projectService.updateProject(project);

        return ResponseEntity.ok(new ResponseWrapper<Transaction>(200,"Transaction successful", transactionService.saveTransaction(transaction)));
    }
}
