package com.HotelBooking.Service;

import com.HotelBooking.entity.Images;
import com.HotelBooking.entity.Property;
import com.HotelBooking.repository.ImagesRepository;
import com.HotelBooking.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageService {

    private PropertyRepository propertyRepository;
    private ImagesRepository imagesRepository;
    private  BucketService bucketService;


    public ImageService(PropertyRepository propertyRepository, ImagesRepository imagesRepository, BucketService bucketService) {
        this.propertyRepository = propertyRepository;
        this.imagesRepository = imagesRepository;
        this.bucketService = bucketService;
    }

    public Images uploadFile(MultipartFile file, String bucketName, long propertyId){
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found with id:"));
        String imageUrl = bucketService.uploadFile(file, bucketName);

        Images images = new Images();
        images.setUrl(imageUrl);
        images.setProperty(property);

        return imagesRepository.save(images);
    }

    public List<Images> getAllImages(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        return imagesRepository.findByProperty(property);
    }
}