package com.sgu.givingsgu.service;

import com.sgu.givingsgu.dto.TopDonorDTO;
import com.sgu.givingsgu.model.Donation;
import com.sgu.givingsgu.repository.DonationRepository;
import com.sgu.givingsgu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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
            return donationRepository.save(donation);
        }
        return null;
    }

    public void deleteDonation(Long donationId) {
        donationRepository.deleteById(donationId);
    }


    public List<TopDonorDTO> getTop10Donors() {
        Pageable topTen = PageRequest.of(0, 10);
        return transactionRepository.findTop10Donors(topTen);
    }

    public Donation findByUserIdAndProjectId(Long userId, Long projectId) {
        return donationRepository.findByUserIdAndProjectId(userId, projectId);
    }
}