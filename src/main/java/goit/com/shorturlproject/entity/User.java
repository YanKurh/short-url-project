package goit.com.shorturlproject.entity;


import goit.com.shorturlproject.url.UrlLink;
import goit.com.shorturlproject.validation.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import goit.com.shorturlproject.validation.PasswordMatching;
import goit.com.shorturlproject.validation.StrongPassword;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
  @Getter
  @Setter
  public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "first_name")
    private String firstName;


    @Column(name = "email")
    private String email;

    @Column(name = "password",length = 60)
    private String password;



    @OneToMany(mappedBy = "user")
    private Set<UrlLink> links = new HashSet<>();

    private boolean isEnabled;


     @Column(name = "login")
      private String login;
  }








