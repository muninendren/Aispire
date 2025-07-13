package com.example.AI.AIspire.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate createdDate;
    private LocalDate targetDate;
    @Email
    private String mail;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Milestone> milestones;

    private Boolean isDone;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDate.now();
    }
}

