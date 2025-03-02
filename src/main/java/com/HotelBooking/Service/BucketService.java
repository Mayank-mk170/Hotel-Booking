package com.HotelBooking.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class BucketService {

    @Autowired
    private AmazonS3 amazonS3;

    public String uploadFile(MultipartFile file, String bucketName){
        if(file.isEmpty()){
            throw new IllegalIdentifierException("Cant upload empty file");
        }
        try{
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            try{
                amazonS3.putObject(bucketName, convFile.getName(), convFile);
                return amazonS3.getUrl(bucketName,file.getOriginalFilename()).toString();
            }catch (AmazonServiceException s3Exception) {
                return "Unable to upload file: " + s3Exception.getMessage();
            }
        }catch (Exception e ){
            throw new IllegalIdentifierException("Failed to upload file: " + e);
        }

    }
}