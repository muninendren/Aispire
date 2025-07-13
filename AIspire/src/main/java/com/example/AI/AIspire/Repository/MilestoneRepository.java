package com.example.AI.AIspire.Repository;

import com.example.AI.AIspire.Entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByDueDate(LocalDate date);

    @Query(value = "SELECT g.description, g.mail, g.target_date, m.description, m.due_date FROM goal g " +
            "JOIN milestone m ON g.id = m.goal_id " +
            "wHERE m.completed = 0 " +
            "AND g.id= ?1 "+
            "ORDER BY m.due_date LIMIT 1",nativeQuery = true)
    List<Object[]> getRecentUnFinishedMilestone(Long id);
}

