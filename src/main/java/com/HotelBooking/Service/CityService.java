package com.HotelBooking.Service;

import com.HotelBooking.entity.City;
import com.HotelBooking.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // ****************  CREATE  ****************

    public City createCity(City city){
        City savedCity = cityRepository.save(city);
        return savedCity;
    }
    // ****************  READ ALL  ****************

    public List<City> readCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    // ******************  UPDATE  ****************

    public City updateCity(Long id, City city){
        City cities = cityRepository.findById(id).get();
              //  orElseThrow(()-> new RuntimeException("City not found with id: " + id));
        cities.setName(city.getName());
        City saveEntity = cityRepository.save(cities);
        return saveEntity;
    }

    // ****************  DELETE  ****************

    public void deleteCity(long id){
        cityRepository.deleteById(id);
    }
}
