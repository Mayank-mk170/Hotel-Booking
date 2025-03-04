package com.HotelBooking.Service;

import com.HotelBooking.payload.SmsDto;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {



    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;



    public String sendSms(SmsDto smsRequest) {
        Message message = Message.creator(
                new PhoneNumber(smsRequest.getTo()), // Receiver's Number
                new PhoneNumber(twilioPhoneNumber),  // Twilio Number
                smsRequest.getMessage()              // Message Content
        ).create();

        return "Message Sent Successfully with SID: " + message.getSid();
    }

}
