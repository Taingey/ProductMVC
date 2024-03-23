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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    @Column(unique = true, nullable = false, length = 40)
    private String name;
    private Integer qty;
    private Double price;
    private LocalDate date;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

}
