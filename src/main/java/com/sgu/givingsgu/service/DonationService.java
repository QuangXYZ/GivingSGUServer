package com.sgu.givingsgu.service;

import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getDonationById(Long donationId) {
        return donationRepository.findById(donationId).orElse(null);
    }

    public Donation createDonation(Donation donation) {
        donation.setDonateDate(new Date()); // Thiết lập ngày donate tự động
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Long donationId, Donation donationDetails) {
        Donation donation = getDonationById(donationId);
        if (donation != null) {
            donation.setProjectId(donationDetails.getProjectId());
            donation.setUserId(donationDetails.getUserId());
            donation.setAmount(donationDetails.getAmount());
            donation.setStatus(donationDetails.getStatus());
            return donationRepository.save(donation);
        }
        return null; // Hoặc ném một exception
    }

    public void deleteDonation(Long donationId) {
        donationRepository.deleteById(donationId);
    }
}