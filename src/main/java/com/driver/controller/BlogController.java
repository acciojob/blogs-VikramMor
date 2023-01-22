package com.driver.controller;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.services.BlogService;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;


    @GetMapping("get_no_of_blogs")
    public ResponseEntity<Integer> getAllBlogs() {
        List<Blog> blogList = blogService.showBlogs();
        int countOfBlogs = blogList.size();

        return new ResponseEntity<>(countOfBlogs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBlog(@RequestParam Integer userId ,@RequestParam String title,@RequestParam String content) {

        blogService.createAndReturnBlog(userId,title,content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable("blogId") int blogId, @RequestParam String description, @RequestParam String dimensions) {
        blogService.addImage(blogId,description,dimensions);
        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable("blogId") int blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
