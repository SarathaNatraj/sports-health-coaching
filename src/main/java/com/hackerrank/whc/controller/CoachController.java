package com.hackerrank.whc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.repository.CoachRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RestController
public class CoachController {

    final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    
    @PostMapping("/api/coach")
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach){
        return new ResponseEntity<Coach>(coachRepository.save(coach), HttpStatus.CREATED);
    }

    @GetMapping("/api/coach")
    public ResponseEntity<List<Coach>> getCoaches() {
        List<Coach> coaches = coachRepository.findAll(Sort.by("id"));
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/api/coach/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Integer id) {
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
