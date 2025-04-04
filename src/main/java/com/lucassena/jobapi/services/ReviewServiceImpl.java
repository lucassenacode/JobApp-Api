package com.lucassena.jobapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
  public boolean createReview(Long companyId, Review review) {

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

  @Override
  public Review getReviewById(@PathVariable("id") Long companyId, @PathVariable("reviewId") Long reviewId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews.stream()
        .filter(review -> review.getId().equals(reviewId))
        .findFirst()
        .orElse(null);
  }

  @Override
  public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
    Review existingReview = getReviewById(companyId, reviewId);
    if (existingReview == null) {
      return false;
    }
    updateReviewAttributes(existingReview, updatedReview);
    reviewRepository.save(existingReview);
    return true;
  }

  private void updateReviewAttributes(Review existingReview, Review newReview) {
    existingReview.setTitle(newReview.getTitle());
    existingReview.setDescription(newReview.getDescription());
    existingReview.setRating(newReview.getRating());
  }

  @Override
  public boolean deleteReview(Long companyId, Long reviewId) {
    Review review = getReviewById(companyId, reviewId);
    if (review == null) {
      return false;
    }

    reviewRepository.delete(review);
    return true;
  }
}
