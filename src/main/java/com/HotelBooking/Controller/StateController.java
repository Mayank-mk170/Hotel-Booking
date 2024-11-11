package com.HotelBooking.Controller;

import com.HotelBooking.Service.StateService;
import com.HotelBooking.entity.City;
import com.HotelBooking.entity.State;
import com.HotelBooking.repository.StateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
public class StateController {
    private StateService stateService;
    private final StateRepository stateRepository;

    public StateController(StateService stateService,
                           StateRepository stateRepository) {
        this.stateService = stateService;
        this.stateRepository = stateRepository;
    }

    @PostMapping("/add-state")
    public ResponseEntity<?> createState(
            @RequestBody State state
    ){
        State saveState = stateService.createState(state);
        return new ResponseEntity<>("State created successfully", HttpStatus.CREATED);
    }

    // ****************  READ ALL  ****************
    @GetMapping("/read-state")
    public ResponseEntity<List<?>> getAllCities(){
        List<State> states =stateService.readCities();
        return new ResponseEntity<>(states, HttpStatus.OK);

    }
    // ****************  UPDATE  *******************

    @PutMapping("/update-state/{id}"  )
    public ResponseEntity<?> updateCity(
            @PathVariable Long id,
            @RequestBody State state
    ){
        State updateCities = stateService.updateCity(id, state);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }
    // ****************  DELETE  ****************

    @DeleteMapping
    public ResponseEntity<String> deleteCity(
            @RequestParam long id
    ){
        stateService.deleteCity(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
