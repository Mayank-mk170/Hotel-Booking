package com.HotelBooking.repository;

import com.HotelBooking.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}