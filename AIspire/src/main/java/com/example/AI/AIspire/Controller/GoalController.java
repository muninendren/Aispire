package com.example.AI.AIspire.Controller;

import com.example.AI.AIspire.Dto.GoalRequest;
import com.example.AI.AIspire.Entity.Goal;
import com.example.AI.AIspire.Services.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    @Autowired
    private final GoalService goalService;

    // 🎯 1. Create a new goal (uses AI to break into milestones)
    @PostMapping
    public ResponseEntity<Goal> createGoal(@Valid @RequestBody GoalRequest request) {
        Goal goal = goalService.createGoal(request.getTitle(), request.getDescription(), request.getTargetDate(), request.getMail());
        return ResponseEntity.ok(goal);
    }

    // 📜 2. Get all goals
    @GetMapping
    public ResponseEntity<List<Goal>> getAllGoals() {
        return ResponseEntity.ok(goalService.getAllGoals());
    }

    // 📌 3. Get a specific goal with milestones
    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        return ResponseEntity.ok(goalService.getGoalById(id));
    }
}

