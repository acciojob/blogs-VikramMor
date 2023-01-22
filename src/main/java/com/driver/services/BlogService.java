package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        return blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {

        Blog blog = new Blog(title,content);
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        List<Blog> currbloglist = user.getBlogList();
        if(currbloglist==null){
            currbloglist=new ArrayList<>();
        }

        currbloglist.add(blog);
        user.setBlogList(currbloglist);
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
        return  blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){

        Blog blog = blogRepository1.findById(blogId).get();
        Image image = imageService1.createAndReturn(blog,description,dimensions);
        image.setBlog(blog);
        List<Image> currimagelist = blog.getImageList();
        if(currimagelist==null){
            currimagelist=new ArrayList<>();
        }
        currimagelist.add(image);
        blog.setImageList(currimagelist);
        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        if(blogRepository1.findById(blogId).isPresent()){
            blogRepository1.deleteById(blogId);
        }
    }
}