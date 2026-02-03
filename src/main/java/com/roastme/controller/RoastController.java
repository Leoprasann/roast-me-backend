package com.roastme.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roastme.dao.LeaderboardDTO;
import com.roastme.dao.RoastHistory;
import com.roastme.service.RoastService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RoastController {

    @Autowired
    private RoastService roastService;

    @GetMapping("/roast")
    public Map<String, String> roast(
            @RequestParam String name,
            @RequestParam(defaultValue = "SOFT") String type) {

        String result = roastService.getRandomRoast(name, type);
        return Map.of("roast", result);
    }

    
    @GetMapping("/history")
    public List<RoastHistory> history() {
        return roastService.getLast5Roasts();
    }
    
    @GetMapping("/leaderboard")
    public List<LeaderboardDTO> leaderboard() {
        return roastService.getLeaderboard();
    }

}

