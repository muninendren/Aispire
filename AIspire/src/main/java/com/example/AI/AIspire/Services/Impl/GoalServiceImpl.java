package com.example.AI.AIspire.Services.Impl;

import com.example.AI.AIspire.Entity.Goal;
import com.example.AI.AIspire.Entity.Milestone;
import com.example.AI.AIspire.Repository.GoalRepository;
import com.example.AI.AIspire.Repository.MilestoneRepository;
import com.example.AI.AIspire.Services.GoalService;
import com.example.AI.AIspire.Services.MailService;
import com.example.AI.AIspire.Services.OllamaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    private final OllamaService ollamaService;

    @Autowired
    private final MailService mailService;

    public Goal createGoal(String title, String description, LocalDate targetDate, String mail) {
        log.info("creating goal started :{}",title);
        // 1. Call AI to get step breakdown
        String aiResponse = ollamaService.getStepsFromAi(title,targetDate.toString(),description);

        // 2. Parse AI response
        List<Milestone> milestones = parseMilestonesFromAI(aiResponse);

        // 3. Create and save goal
        Goal goal = Goal.builder()
                .title(title)
                .description(description)
                .targetDate(targetDate)
                .milestones(new ArrayList<>())
                .isDone(Boolean.FALSE)
                .mail(mail)
                .build();

        // 4. Set goal to milestones and save
        for (int i = 0; i < milestones.size(); i++) {
            Milestone m = milestones.get(i);
            m.setGoal(goal);
            m.setDueDate(LocalDate.now().plusWeeks(i + 1));
            goal.getMilestones().add(m);
        }

        return goalRepository.save(goal);
    }

    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    public Goal getGoalById(Long id) {
        return goalRepository.findById(id).orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    @PostConstruct
    private void sendMessagesGoal(){

        List<Goal> inCompleteGoals=goalRepository.findAllByIsDoneIsFalse();

        inCompleteGoals.forEach(goal -> {
            sendMessagesMilestone(goal.getId());
        });

    }

    private void sendMessagesMilestone(Long id){
        List<Object[]> milestoneVal=milestoneRepository.getRecentUnFinishedMilestone(id);

        try{
            if(milestoneVal != null && !milestoneVal.isEmpty()){
                String mail=(String)milestoneVal.get(0)[1];
                String goal= (String)milestoneVal.get(0)[0];
                String date = milestoneVal.get(0)[2].toString();
                String desc=(String)milestoneVal.get(0)[3];
                String subject="Its an remainder for your GOAL -"+goal+" With in "+date;
                String body="we believing you currently on the phase of \n"+
                        desc +"\n"+
                        "Keep going You can do anything";

                mailService.sendMail(mail, subject, body);
                log.info("Mesage sent to id:{} mail: {} with subject {}",id,mail,subject);
            }
            else{
                log.info("mail failed for {} | Reason: {}",id,"No mile stone");
            }
        }
        catch (Exception e){
            log.info("mail failed for {} | Reason:{}",id,e.getMessage());
        }


    }

    private List<Milestone> parseMilestonesFromAI(String response) {
        List<Milestone> milestones = new ArrayList<>();
        String[] lines = response.split("\n");

        boolean isLine = true;

        for(int i=2;i<lines.length;i+=3){
           try{
               Milestone milestone=Milestone.builder()
                       .description(lines[i])
                       .dueDate(LocalDate.parse(lines[i+1]))
                       .build();
               milestones.add(milestone);
           } catch (Exception e) {
               log.info(e.getMessage());
           }
        }

        return milestones;
    }

    public Milestone parseMilestoneLine(String line) throws Exception {
        // Trim and split line at the last space (before date)
        int lastSpace = line.lastIndexOf(' ');
        String description = line.substring(0, lastSpace).replaceFirst("^\\d+\\.?", "").trim();
        String datePart = line.substring(lastSpace + 1).trim();

        LocalDate date = LocalDate.parse(datePart); // ISO format: yyyy-MM-dd

        Milestone milestone=Milestone.builder().description(description).dueDate(date).build();
        return milestone ;
    }

}

