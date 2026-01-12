package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.service.MatchingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
@CrossOrigin
public class MatchingController {

    private final MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    // MATCH BEST AGENT BY AREA
    @GetMapping("/agent")
    public Agent matchAgent(@RequestParam String area) {
        return matchingService.matchBestAgent(area);
    }
}
