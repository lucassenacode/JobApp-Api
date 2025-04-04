package com.lucassena.jobapi.services;

import java.util.List;

import com.lucassena.jobapi.entities.Review;

public interface ReviewService {

  boolean addReview(Long companyId, Review review);

  List<Review> getAllReviews(Long companyId);

}
