package com.example.AI.AIspire.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate dueDate;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    @JsonBackReference
    private Goal goal;
}

