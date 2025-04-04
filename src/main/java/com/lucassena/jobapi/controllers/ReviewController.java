package com.lucassena.jobapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucassena.jobapi.entities.Review;
import com.lucassena.jobapi.services.ReviewService;

@RestController
@RequestMapping("/companies/{id}/reviews")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @PostMapping
  public ResponseEntity<String> createReview(@PathVariable Long id, @RequestBody Review review) {
    boolean created = reviewService.createReview(id, review);
    if (!created) {
      return new ResponseEntity<>("Erro: Empresa inválida ou inexistente.", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Review adicionado com sucesso", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id) {
    List<Review> reviews = reviewService.getAllReviews(id);
    if (reviews.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity.ok(reviews);
  }

  @GetMapping("/{reviewId}")
  public ResponseEntity<Review> getReviewById(@PathVariable("id") Long companyId, @PathVariable Long reviewId) {
    Review review = reviewService.getReviewById(companyId, reviewId);
    if (review != null) {
      return ResponseEntity.ok(review);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{reviewId}")
  public ResponseEntity<String> updateReview(@PathVariable("id") Long companyId, @PathVariable Long reviewId,
      @RequestBody Review review) {
    boolean updated = reviewService.updateReview(companyId, reviewId, review);
    if (updated) {
      return ResponseEntity.ok("Review atualizado com sucesso");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<String> deleteReview(@PathVariable("id") Long companyId, @PathVariable Long reviewId) {
    boolean deleted = reviewService.deleteReview(companyId, reviewId);
    if (deleted) {
      return ResponseEntity.ok("Review deletado com sucesso");
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
