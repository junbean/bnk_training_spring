package com.example.theater.dto;


import java.util.Date;

import lombok.Data;

@Data
public class PerformanceDTO {
    private Long performanceId;
    private String title;
    private String genre;
    private String description;
    private int duration;
    private String director;
    private String cast;
    private String ageLimit;
    private int price;
    private String status; 		// UPCOMING, ONGOING, CLOSED
    private Date startDate;
    private Date endDate;
    private String imageUrl;
    private int totalSeats;
    private int remainingSeats;
}

/*
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
*/