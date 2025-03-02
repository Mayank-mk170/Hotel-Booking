package com.HotelBooking.repository;

import com.HotelBooking.entity.Images;
import com.HotelBooking.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images, Long> {
  List<Images> findByProperty(Property property);
}