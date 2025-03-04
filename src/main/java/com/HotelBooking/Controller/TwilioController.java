package com.HotelBooking.Controller;

import com.HotelBooking.Service.TwilioService;
import com.HotelBooking.payload.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
public class TwilioController {

    @Autowired
    private TwilioService twilioService;


    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody SmsDto smsRequest) {
        String response = twilioService.sendSms(smsRequest);
        return ResponseEntity.ok(response);
    }
}