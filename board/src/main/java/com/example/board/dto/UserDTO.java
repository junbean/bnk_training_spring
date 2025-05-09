package com.example.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;    
    private String password;    
    private String name;        
    private String email;     
    private String phone;           
    private Character role;   
}