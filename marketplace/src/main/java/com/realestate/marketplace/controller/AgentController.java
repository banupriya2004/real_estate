package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.service.AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    // ✅ ADD AGENT
    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    // ✅ GET ALL AGENTS
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    // ✅ GET AGENT BY ID
    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id);
    }

    // ✅ GET AGENTS BY LOCATION
    @GetMapping("/location/{location}")
    public List<Agent> getAgentsByLocation(@PathVariable String location) {
        return agentService.getAgentsByLocation(location);
    }


    // ✅ UPDATE AGENT
    @PutMapping("/{id}")
    public Agent updateAgent(@PathVariable Long id, @RequestBody Agent agent) {
        return agentService.updateAgent(id, agent);
    }

    // ✅ DELETE AGENT
    @DeleteMapping("/{id}")
    public String deleteAgent(@PathVariable Long id) {
        boolean deleted = agentService.deleteAgentById(id);
        return deleted ? "Agent deleted successfully" : "Agent not found";
    }
}
