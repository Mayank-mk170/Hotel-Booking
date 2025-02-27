package com.HotelBooking.Controller;

import com.HotelBooking.Service.ReviewService;
import com.HotelBooking.entity.AppUser;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.Review;
import com.HotelBooking.repository.PropertyRepository;
import com.HotelBooking.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    private ReviewService reviewService; // Assuming ReviewService is defined elsewhere and provides getUserReviews method.

    public ReviewController( ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add-review")
    public ResponseEntity<?> write(
            @RequestBody Review review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser user){
        Object response = reviewService.writeReview(review, propertyId, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/review")
    public ResponseEntity<List<Review>> getUserReviews(
            @AuthenticationPrincipal AppUser user){
        List<Review> reviews = reviewService.getUserReviews(user);
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }

}
