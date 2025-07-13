package com.example.AI.AIspire.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalRequest {
    private String title;
    private String description;
    private LocalDate targetDate;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String mail;
}

