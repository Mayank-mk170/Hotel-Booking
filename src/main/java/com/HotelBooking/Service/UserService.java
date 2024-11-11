package com.HotelBooking.Service;

import com.HotelBooking.entity.AppUser;
import com.HotelBooking.payload.AppUserDto;
import com.HotelBooking.payload.LoginDto;
import com.HotelBooking.repository.AppUserRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    // *********************   SIGNUP AS USER  *******************

    public ResponseEntity<?> createUser(AppUser userDto) {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("UserName already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        AppUser user = modelMapper.map(userDto, AppUser.class);
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(5));
        userDto.setPassword(encryptedPassword);

        userDto.setRole("ROLE_USER");

        AppUser savedUser = appUserRepository.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    AppUserDto mapToDto(User user) {
        AppUserDto dto = modelMapper.map(user,AppUserDto.class);
        return dto;
    }
    AppUser mapToEntity(AppUserDto appUserDto){
        AppUser user = modelMapper.map(appUserDto, AppUser.class);
        return user;
    }

    // *************************  LOGIN  *****************

    public String verifyLogin(LoginDto loginDto){
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
            if( BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {
                //generate token
                String token = jwtService.generateToken(appUser.getUsername());
                return token;
            }
            }else {
            return null;
        }
        return null;
        }

        // ***********************  SIGNUP AS OWNER  ******************

    public ResponseEntity<?> createPropertyOwnerUser(AppUser userDto) {
        Optional<AppUser> opUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("UserName already taken", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> opEmail = appUserRepository.findByEmail(userDto.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email already taken",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // AppUser user = modelMapper.map(userDto, AppUser.class);
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(5));
        userDto.setPassword(encryptedPassword);

        userDto.setRole("ROLE_OWNER");

        AppUser savedUser = appUserRepository.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
