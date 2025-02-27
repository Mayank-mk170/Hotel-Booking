package com.HotelBooking.Controller;

import com.HotelBooking.Service.PropertyService;
import com.HotelBooking.entity.Property;
import com.HotelBooking.payload.PropertyDto;
import com.HotelBooking.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {

        this.propertyService = propertyService;
    }

    // Search Hotels
    @GetMapping("/search-hotels")
    public List<Property> searchHotels(
            @RequestParam (name = "hotel_name") String hotelName

    ){
        return propertyService.searchHotels(hotelName);
    }

    @PostMapping("/addProperty")
    public ResponseEntity<?> addProperty(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long country_id,
            @RequestParam long city_id,
            @RequestParam long state_id
            ){
        Property properties  = propertyService.addProperty(propertyDto,country_id,city_id,state_id);
        if(properties == null){
            return new ResponseEntity<>("property not found", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(properties, HttpStatus.CREATED);
    }



}
