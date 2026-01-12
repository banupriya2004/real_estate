package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.service.AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    // ADD AGENT
    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    // GET ALL AGENTS
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    // GET AGENTS BY AREA
    @GetMapping("/area/{area}")
    public List<Agent> getAgentsByArea(@PathVariable String area) {
        return agentService.getAgentsByArea(area);
    }
}
