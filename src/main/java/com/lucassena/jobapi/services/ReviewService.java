package com.lucassena.jobapi.services;

import java.util.List;

import com.lucassena.jobapi.entities.Review;

public interface ReviewService {

  boolean createReview(Long companyId, Review review);

  List<Review> getAllReviews(Long companyId);

  Review getReviewById(Long companyId, Long reviewId);

  boolean updateReview(Long companyId, Long reviewId, Review review);

  boolean deleteReview(Long companyId, Long reviewId);

}
