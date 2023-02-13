package com.mousam.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_generator")
    @SequenceGenerator(name = "item_id_generator", sequenceName = "item_id_sequence", allocationSize = 1)
    @Column(nullable = false)
    private Long id;
    @ManyToOne(targetEntity = AppUser.class)
    private String user_name;
    private String name;
    private Boolean is_active;

    public Item(String user_name, String name, Boolean is_active) {
        this.user_name = user_name;
        this.name = name;
        this.is_active = is_active;
    }
}
