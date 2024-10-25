package com.sgu.givingsgu.controller;

import com.sgu.givingsgu.dto.TopDonorDTO;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }

    @PostMapping
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);
    }

    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
    }

    @PutMapping("/{id}")
    public Donation updateDonation(@PathVariable Long id, @RequestBody Donation donationDetails) {
        return donationService.updateDonation(id, donationDetails);
    }
    @GetMapping("/top10")
    public ResponseEntity<List<TopDonorDTO>> getTop10Donors() {
        List<TopDonorDTO> topDonors = donationService.getTop10Donors();
        return new ResponseEntity<>(topDonors, HttpStatus.OK);
    }

    @GetMapping("/top10/{projectId}")
    public ResponseEntity<List<TopDonorDTO>> getTop10Donors(@PathVariable Long projectId) {
        List<TopDonorDTO> topDonors = donationService.getTop10DonorsByProjectId(projectId);
        return new ResponseEntity<>(topDonors, HttpStatus.OK);
    }
}
