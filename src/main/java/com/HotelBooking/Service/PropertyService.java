package com.HotelBooking.Service;

import com.HotelBooking.entity.City;
import com.HotelBooking.entity.Country;
import com.HotelBooking.entity.Property;
import com.HotelBooking.entity.State;
import com.HotelBooking.payload.PropertyDto;
import com.HotelBooking.repository.CityRepository;
import com.HotelBooking.repository.CountryRepository;
import com.HotelBooking.repository.PropertyRepository;
import com.HotelBooking.repository.StateRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;


    public PropertyService(PropertyRepository propertyRepository,
                           CountryRepository countryRepository,
                           CityRepository cityRepository,
                           StateRepository stateRepository) {
        this.propertyRepository = propertyRepository;

        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public List<Property> searchHotels(String name) {
        return propertyRepository.searchHotels(name);
    }

    //  Property property = modelMapper.map(propertyDto, Property.class);
    //  Optional<Country> country = CountryRepository.findById(country_id)
    public Property addProperty(PropertyDto propertyDto, long country_id, long city_id, long state_id) {


        Optional<Country> countries = countryRepository.findById(country_id);
        if(!countries.isPresent()){
            return null;
        }
        Optional<City> cities = cityRepository.findById(city_id);
        if(!cities.isPresent()){
            return null;
        }
        Optional<State> states = stateRepository.findById(state_id);
        if(!states.isPresent()){
            return null;
        }

        Country co = countries.get();
        City ct = cities.get();
        State st = states.get();
        Property po = new Property();
        po.setHotel_name(propertyDto.getHotel_name()); // Set the hotel_name here
        po.setNo_of_guest(propertyDto.getNo_of_guest());
        po.setNo_of_bedrooms(propertyDto.getNo_of_beds()); // Assuming no_of_beds corresponds to no_of_bedrooms
        po.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
        po.setNo_of_beds(propertyDto.getNo_of_beds());
        po.setCountry(co);
        po.setCity(ct);
        po.setState(st);

       return propertyRepository.save(po);
    }


    public Property getPropertyById(long propertyId) {
        return propertyRepository.findById(propertyId).get();

    }
}
