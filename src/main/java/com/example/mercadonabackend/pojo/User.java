package com.example.mercadonabackend.pojo;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User  {

    @Id

    @SequenceGenerator(name="user_seq",
            sequenceName = "user_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
