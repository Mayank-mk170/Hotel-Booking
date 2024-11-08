package com.HotelBooking.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppUserDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}
