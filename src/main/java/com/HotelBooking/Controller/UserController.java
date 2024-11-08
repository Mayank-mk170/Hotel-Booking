package com.HotelBooking.Controller;

import com.HotelBooking.Service.UserService;
import com.HotelBooking.entity.AppUser;
import com.HotelBooking.payload.AppUserDto;
import com.HotelBooking.payload.LoginDto;
import com.HotelBooking.payload.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


                // ************ SIGNUP AS USER  *****************

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody AppUser userDto) {
        return userService.createUser(userDto);
    }

    //  *********************  SIGNUP AS PROPERTY OWNER  *******************
    //http://localhost:8080/api/v1/users/owner
    @PostMapping("/signup-property-owner")
    public ResponseEntity<?> createPropertyOwnerUser(
            @RequestBody AppUser userDto) {
        return userService.createPropertyOwnerUser(userDto);
    }
    //http://localhost:8080/api/v1/users/owner/login

          //****************    LOGIN  ********************

    //http://localhost:8080/login
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody  LoginDto dto
    ){
        String token = userService.verifyLogin(dto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
        }

    }
}
