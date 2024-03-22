package com.istad.demo.dto;

public record ProductRespons(
        String uuid,
        String name,
        Double price,
        Integer qty
) {
}
