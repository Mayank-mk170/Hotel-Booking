package com.HotelBooking.Service;

import com.HotelBooking.entity.AppUser;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.Review;
import com.HotelBooking.repository.PropertyRepository;
import com.HotelBooking.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewService {

    private final PropertyRepository propertyRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }

     @Transactional
    public Object writeReview(Review review, long propertyId, AppUser user) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found with ID: " + propertyId));

        if(reviewRepository.existsByAppUserAndProperty(user, property)){
            return "Review Already Exist";

        }
        review.setProperty(property);
        review.setAppUser(user);
        Review saveReview = reviewRepository.save(review);
        return reviewRepository.save(review);
    }

    public List<Review> getUserReviews(AppUser user) {
        return reviewRepository.findByAppUser(user);
    }


}
