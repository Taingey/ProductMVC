package com.istad.demo.modol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Integer id;
    private String uuid;
    private String name;
    private Integer qty;
    private Double price;
    private LocalDate date;
    private boolean status;

    @ManyToOne
    private Category category;


    public boolean getStatus() {
        return status;
    }

}
