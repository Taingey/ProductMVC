package com.istad.demo.modol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String uuid;

    @Column(unique = true, nullable = false, length = 40)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;


}
