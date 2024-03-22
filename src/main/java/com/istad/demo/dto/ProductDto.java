package com.istad.demo.dto;

public record ProductDto (
        String uuid,
        String name,
        Integer qty,
        Double price
){
}
