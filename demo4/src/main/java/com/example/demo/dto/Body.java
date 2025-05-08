package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public
class Body {
    @JsonProperty("items")
    private Items items;

    @JsonProperty("numOfRows")
    private int numOfRows;

    @JsonProperty("pageNo")
    private int pageNo;

    @JsonProperty("totalCount")
    private int totalCount;
}