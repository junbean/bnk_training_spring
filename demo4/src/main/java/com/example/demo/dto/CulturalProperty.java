package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CulturalProperty {
    @JsonProperty("DOJIJUNG_NO")
    private String dojijungNo;
    @JsonProperty("MYONGCHING")
    private String myongching;
    @JsonProperty("MYONGCHING_HANMUN")
    private String myongchingHanmun;
    @JsonProperty("CONTENT")
    private String content;
    @JsonProperty("MYONJUK")
    private String myonjuk;
    @JsonProperty("SOYOUJA_NAME")
    private String soyoujaName;
    @JsonProperty("ADMIN_NAME")
    private String adminName;
    @JsonProperty("SIDAE")
    private String sidae;
    @JsonProperty("JIJUNG_DATE")
    private String jijungDate;
    @JsonProperty("ADDRESS1")
    private String address1;
    @JsonProperty("UTMK_X")
    private String utmkX;
    @JsonProperty("UTMK_Y")
    private String utmkY;
    @JsonProperty("fileurl1")
    private String fileurl1;
    @JsonProperty("fileurl2")
    private String fileurl2;
    @JsonProperty("fileurl3")
    private String fileurl3;
}