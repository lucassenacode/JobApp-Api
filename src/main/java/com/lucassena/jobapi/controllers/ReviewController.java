package com.lucassena.jobapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<String> addReview(@PathVariable Long id, @RequestBody Review review) {
    boolean created = reviewService.addReview(id, review);
    if (!created) {
      return new ResponseEntity<>("Erro: Empresa invalida ou inexistente.", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Review adicionado com seuceseeo", HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long id) {
    List<Review> reviews = reviewService.getAllReviews(id);
    if (reviews.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity.ok(reviews);
  }

}
