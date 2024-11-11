package com.HotelBooking.repository;

import com.HotelBooking.entity.AppUser;
import com.HotelBooking.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

   // Optional<Property> findByHotelName(String hotel_name);

    @Query("SELECT p FROM Property p JOIN p.city c JOIN p.country co WHERE c.name = :name or co.name = :name")
    List<Property> searchHotels(@Param("name") String name);

}