package com.HotelBooking.repository;

import com.HotelBooking.entity.AppUser;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAppUser(AppUser user);
    boolean existsByAppUserAndProperty(AppUser appUser, Property property);}