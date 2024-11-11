package com.HotelBooking.payload;

import com.HotelBooking.entity.City;
import com.HotelBooking.entity.Country;
import com.HotelBooking.entity.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private String hotel_name;
    private Integer no_of_rooms;
    private Integer no_of_guest;
    private Integer no_of_beds;
    private Integer no_of_bathrooms;

    private Country country;
    private Long country_id;

    private City city;
    private Long city_id;

    private State state;
    private Long state_id;
}
