package com.HotelBooking.Controller;

import com.HotelBooking.Service.CountryService;
import com.HotelBooking.entity.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    // ****************  CREATE  ****************

    @PostMapping("/add-country")
    public ResponseEntity<String> addCountry(
            @RequestBody Country country
    ) {
        Country saveCountry = countryService.createCountry(country);
        return new ResponseEntity<>("Country added successfully", HttpStatus.CREATED);

    }

    // ****************  READ ALL  ****************

    @GetMapping("/read-countrys"  )
    public ResponseEntity<List<?>> getAllCountrys(){
        List<Country> countries = countryService.readCountrys();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
    // ****************  UPDATE  *******************
    @PutMapping("/update-country/{id}")
    public ResponseEntity<?> updateCountry(
            @PathVariable Long id,
            @RequestBody Country country
    ){
        Country updateCountry = countryService.updateCountry(id, country);
        return new ResponseEntity<>("Country updated successfully", HttpStatus.OK);
    }

    // ****************** DELETE  ****************
    @DeleteMapping
    public ResponseEntity<String> deleteCountry(
            @RequestParam long id
    ){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

}
