package com.example.AI.AIspire.Services;

import com.example.AI.AIspire.Entity.Goal;

import java.time.LocalDate;
import java.util.List;

public interface GoalService {
    public Goal createGoal(String title, String description, LocalDate targetDate, String mail);

    public List<Goal> getAllGoals();

    public Goal getGoalById(Long id);
}



