package com.HotelBooking;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncriptedPassword {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("password", BCrypt.gensalt(5)));
    }
}