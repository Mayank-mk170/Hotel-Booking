package com.HotelBooking.Service;

import com.HotelBooking.entity.Country;
import com.HotelBooking.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CountryService {
    private CountryRepository countryRepository;
    public CountryService(ModelMapper modelMapper, CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    // ****************  CREATE  ****************

    public Country createCountry(Country country) {
        Country saveEntity = countryRepository.save(country);
        return saveEntity;
    }

    // ****************  READ ALL  ****************

    public List<Country> readCountrys() {
        List<Country> countries = countryRepository.findAll();
       // List<CountryDto> countryDtos = countries.stream().map(r-> mapToDto(r)).collect(Collectors.toList());
        return countries;
    }

    // ******************  UPDATE  ****************

    public Country updateCountry(Long id, Country country) {
        Country countrys = countryRepository.findById(id).get();
        countrys.setName(country.getName());
        Country saveEntity = countryRepository.save(countrys);
        return saveEntity;
    }



    // ****************  DELETE  ****************

    public void deleteCountry(long id) {
        countryRepository.deleteById(id);
    }
}
