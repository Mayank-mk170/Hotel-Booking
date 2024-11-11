package com.HotelBooking.Controller;

import com.HotelBooking.Service.CityService;
import com.HotelBooking.entity.City;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    // ****************  CREATE  ****************

    @PostMapping("/create-city")
    public ResponseEntity<?> createCity(
            @RequestBody City city
    ){
        City createCity = cityService.createCity(city);
        return new ResponseEntity<>("City added successfully", HttpStatus.CREATED);

    }

    // ****************  READ ALL  ****************
    @GetMapping("/read-cities")
    public ResponseEntity<List<?>> getAllCities(){
        List<City> cities =cityService.readCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);

    }
    // ****************  UPDATE  *******************

    @PutMapping("/update-city/{id}"  )
    public ResponseEntity<?> updateCity(
            @PathVariable Long id,
            @RequestBody City city
    ){
        City updateCities = cityService.updateCity(id, city);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }
    // ****************  DELETE  ****************

    @DeleteMapping
    public ResponseEntity<String> deleteCity(
            @RequestParam long id
    ){
        cityService.deleteCity(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

}
