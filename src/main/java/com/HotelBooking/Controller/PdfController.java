package com.HotelBooking.Controller;

import com.HotelBooking.Service.PdfService;
import com.HotelBooking.Service.PropertyService;
import com.HotelBooking.entity.Bookings;
import com.HotelBooking.entity.Property;
import com.HotelBooking.repository.BookingsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pdf")
public class PdfController {

    private PdfService pdfService;
    private PropertyService propertyService;
    private BookingsRepository bookingsRepository;

    public PdfController(PdfService pdfService, PropertyService propertyService, BookingsRepository bookingsRepository) {
        this.pdfService = pdfService;
        this.propertyService = propertyService;
        this.bookingsRepository = bookingsRepository;
    }

    @PostMapping("/create-booking/{propertyId}")
    public String createBookingPdf(
            @PathVariable long propertyId,
            @RequestBody Bookings bookings
            ){
        Property  property = propertyService.getPropertyById(propertyId);
        Bookings saveBooking = bookingsRepository.save(bookings);
        String filePath = "D:\\Project\\Spring boot\\trivago\\Booking_Pdf\\confirmation-order "+saveBooking.getId() +".pdf";

                pdfService.generateBookingPdf(filePath, property);
        return "Booking PDF created successfully";
        // pdfService.generateBookingPdf("D:\\Project\\Spring boot\\trivago\\Booking_Pdf\\Pdf_File.pdf");
    }


}
