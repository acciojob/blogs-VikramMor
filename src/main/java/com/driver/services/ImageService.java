package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository2;

    public Image createAndReturn(Blog blog, String description, String dimensions){

        Image image = new Image();
        image.setDimensions(dimensions);
        image.setDescription(description);
        image.setBlog(blog);
        List<Image> imageList = new ArrayList<>();
        imageList = blog.getImageList();
        if(imageList==null){
            imageList=new ArrayList<>();
        }
        imageList.add(image);
        blog.setImageList(imageList);
//        imageRepository2.save(image);
        blogRepository.save(blog);
        return image;
    }

    public void deleteImage(Image image){
        if(imageRepository2.findById(image.getId()).isPresent()){
            imageRepository2.delete(image);
        }
    }

    public Image findById(int id)
    {
        if(imageRepository2.findById(id).isPresent()){
            return imageRepository2.findById(id).get();
        }
        return null;
    }

    public int countImagesInScreen(Image image, String screenDimensions) {

        if (screenDimensions.split("X").length == 2 || Objects.nonNull(image)) {
            Integer maxLength = Integer.parseInt(screenDimensions.split("X")[0]) / Integer.parseInt(image.getDimensions().split("X")[0]);
            Integer maxWidth = Integer.parseInt(screenDimensions.split("X")[1]) / Integer.parseInt(image.getDimensions().split("X")[1]);

            int count = maxWidth * maxLength;
            return count;
        }
        return 0;
    }
}