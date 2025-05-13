package com.example.theater.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long reviewId;
    private Long performanceId;
    private String userId;
    private String reviewContent;
    private Date createdDate;
    private Date modifiedDate;
    private Character isPublic;

}
