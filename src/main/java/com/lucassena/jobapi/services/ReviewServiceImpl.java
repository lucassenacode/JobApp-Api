package com.lucassena.jobapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucassena.jobapi.entities.Company;
import com.lucassena.jobapi.entities.Review;
import com.lucassena.jobapi.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private CompanyService companyService;

  @Override
  public boolean addReview(Long companyId, Review review) {

    Company company = companyService.getCompanyById(companyId);
    if (company == null) {
      return false;
    }
    review.setCompany(company);
    reviewRepository.save(review);
    return true;
  }

  @Override
  public List<Review> getAllReviews(Long companyId) {

    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews;
  }

}
