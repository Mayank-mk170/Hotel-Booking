package com.HotelBooking.Controller;

import com.HotelBooking.Service.BucketService;
import com.HotelBooking.Service.ImageService;
import com.HotelBooking.entity.AppUser;
import com.HotelBooking.entity.Images;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    public BucketService bucketService;
    private ImageService imageService;

    public ImageController(BucketService bucketService, ImageService imageService) {
        this.bucketService = bucketService;
        this.imageService = imageService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable long propertyId,
            @AuthenticationPrincipal AppUser user
    ) {

        Images saveImage = imageService.uploadFile(file, bucketName, propertyId);
        return ResponseEntity.ok(saveImage);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Images>> getAllImages(
            @PathVariable long propertyId
    ) {
        List<Images> propertyImages = imageService.getAllImages(propertyId);
        return ResponseEntity.ok(propertyImages);
    }
}