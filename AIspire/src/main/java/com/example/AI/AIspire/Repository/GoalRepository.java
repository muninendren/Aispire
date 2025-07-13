package com.example.AI.AIspire.Repository;

import com.example.AI.AIspire.Entity.Goal;
import com.example.AI.AIspire.Entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByIsDoneIsFalse();


}

