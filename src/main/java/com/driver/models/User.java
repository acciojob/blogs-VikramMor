package com.driver.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    public User(String username, String password, String firstName, String lastName) {
        this.userName = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogLists;

    public List<Blog> getBlogLists() {
        return blogLists;
    }

    public void setBlogLists(List<Blog> blogLists) {
        this.blogLists = blogLists;
    }
}
