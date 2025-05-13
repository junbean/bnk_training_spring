package com.example.theater.dto;

import lombok.Data;

@Data
public class PerformanceDTO {
    private Long performance_id;
    private String title;
    private String category;
    private String description;
    private int duration;
    private String director;
    private String cast;
    private String rating;
    private int price;
    private String status;
}
