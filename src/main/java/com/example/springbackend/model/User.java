package com.example.springbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String surname;

    private String password;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
    private List<TodoList> todoLists;


}
