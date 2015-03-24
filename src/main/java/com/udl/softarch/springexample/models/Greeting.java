package com.udl.softarch.springexample.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by davidkaste on 23/02/15.
 */

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "This field cannot be blank")
    @Size(max = 256, message = "Content maximum length is {max} characters long")
    private String content;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    public Greeting() {}

    public Greeting(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return Id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
