package com.pocketplanner.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserUpdatePassword {
    private String password;
}