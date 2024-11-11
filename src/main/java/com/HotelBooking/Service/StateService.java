package com.HotelBooking.Service;

import com.HotelBooking.entity.City;
import com.HotelBooking.entity.State;
import com.HotelBooking.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }
    public State createState(State state){
        State saveState = stateRepository.save(state);
        return saveState;
    }

    // ****************  READ ALL  ****************

    public List<State> readCities() {
        List<State> cities = stateRepository.findAll();
        return cities;
    }

    // ******************  UPDATE  ****************

    public State updateCity(Long id, State state){
        State statess = stateRepository.findById(id).get();
        //  orElseThrow(()-> new RuntimeException("City not found with id: " + id));
        statess.setName(state.getName());
        State saveEntity = stateRepository.save(statess);
        return saveEntity;
    }

    // ****************  DELETE  ****************

    public void deleteCity(long id){
        stateRepository.deleteById(id);
    }
}
