package com.mousam.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    private String user_name;
    @OneToMany(mappedBy = "user_name")
    private Set<Item> items;

    public AppUser(String user_name) {
        this.user_name = user_name;
    }
}
