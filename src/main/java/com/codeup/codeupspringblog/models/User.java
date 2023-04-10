package com.codeup.codeupspringblog.models;

import com.codeup.codeupspringblog.models.Post;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class User {
    public User(User copy){
        id = copy.id; // copy users from database
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(length = 50)
    private String first_name;

    @Column(length = 50)
    private String last_name;

    @Column(length = 15)
    private String phone_number;

    @Column(columnDefinition = "ENUM('M','F','NA')")
    private String gender;

    @Column(length = 255)
    private String address;
    @Column(length = 5)
    private int zip_code;

    @Column(columnDefinition = "LONGTEXT")
    private String bio;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Post> posts;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String first_name, String last_name, String phone_number, String gender, String bio, String address, int zip_code, List<Post> posts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.gender = gender;
        this.bio = bio;
        this.address = address;
        this.zip_code = zip_code;
        this.posts = posts;
    }


    //    @Override
//    public String toString() {
//        return "User{" +
//                "username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

    public static void main(String[] args) {
        User user = new User("osj3693","osj3693@gmail.com","1234");
        User user2 = new User(user);
        System.out.println(user);
        System.out.println(user2); // has the same value, but different tag number
    }
}