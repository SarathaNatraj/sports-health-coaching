package com.hackerrank.whc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.repository.CoachRepository;
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
}
