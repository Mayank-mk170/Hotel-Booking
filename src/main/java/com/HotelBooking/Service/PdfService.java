package com.HotelBooking.Service;

import com.HotelBooking.entity.Property;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfService {

    public void generateBookingPdf(String filePath, Property property) {
       try {

           // Ensure directory exists
           File file = new File(filePath);
           file.getParentFile().mkdirs();

           // Initialize PDF document
           Document document = new Document();
           PdfWriter.getInstance(document, new FileOutputStream(file));
           document.open();

           // Add title
           Paragraph title = new Paragraph("Booking Confirmation");
           title.setAlignment(Element.ALIGN_CENTER);
           document.add(title);

           PdfPTable table = new PdfPTable(2);
           table.addCell("Id");
           table.addCell(property.getId().toString());
           table.addCell("Hotel Name");
           table.addCell(property.getHotel_name());
           table.addCell("no of guest");
           table.addCell(property.getNo_of_guest().toString());
           table.addCell("no_of_beds");
           table.addCell(property.getNo_of_beds().toString());

           // Add table to document
           document.add(table);
           document.close();
       }catch (Exception e){
           throw new RuntimeException("Error generating PDF: ", e);
       }
    }

    /*
    try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            PdfPTable table = new PdfPTable(2);

            table.addCell("id");
            table.addCell(property.getId().toString());
            table.addCell("Name");
            table.addCell(property.getHotel_name());
            table.addCell("no of guest");
            table.addCell(property.getNo_of_guest().toString());
            document.add(table);
            document.close();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Welcome");
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
     */
}
