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
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        List<Blog> list = new ArrayList<>();
        list=blogRepository1.findAll();
        return list;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {

        Blog blog= new Blog();
        blog.setContent(content);
        blog.setTitle(title);

        blog.setUser(userRepository1.findById(userId).get());

        User user= userRepository1.findById(userId).get();

        List<Blog> blogLists=user.getBlogList();

        blogLists.add(blog);

        user.setBlogList(blogLists);

        blogRepository1.save(blog);

        userRepository1.save(user);

    }

    public Blog findBlogById(int blogId){
        Blog blog=new Blog();
        blog = blogRepository1.findById(blogId).get();
        return  blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){

        Blog blog= blogRepository1.findById(blogId).get();


        Image image=imageService1.createAndReturn(blog,description,dimensions);
        image.setBlog(blog);

        List<Image> imageList=blog.getImageList();

        if(imageList==null) imageList=new ArrayList<>();

        imageList.add(image);

        blog.setImageList((ArrayList<Image>) imageList);


        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        Blog blog= blogRepository1.findById(blogId).get();

        List<Image> imageList=new ArrayList<>();

        imageList=blog.getImageList();
        for(Image image: imageList){
            imageRepository.delete(image);
        }

        User user= new User();
        user=blog.getUser();
        List<Blog> blogList=new ArrayList<>();

        blogList =user.getBlogList();

        blogList.remove(blog);

        user.setBlogList(blogList);

        blogRepository1.delete(blog);

        userRepository1.save(user);
    }
}