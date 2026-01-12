package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.BuyerAgentMapping;
import com.realestate.marketplace.service.BuyerAgentMappingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mappings")
@CrossOrigin(origins = "http://localhost:5173") // Vite default
public class BuyerAgentMappingController {

    private final BuyerAgentMappingService mappingService;

    // ✅ ONLY ONE CONSTRUCTOR
    public BuyerAgentMappingController(BuyerAgentMappingService mappingService) {
        this.mappingService = mappingService;
    }

    // 🔹 BUY NOW → ASSIGN AGENT
    @PostMapping("/assign")
    public BuyerAgentMapping assignAgent(
            @RequestParam Long buyerId,
            @RequestParam Long propertyId) {
        return mappingService.assignAgent(buyerId, propertyId);
    }

    // 🔹 BUYER → GET ASSIGNED AGENTS
    @GetMapping("/buyer/{buyerId}/agents")
    public ResponseEntity<List<Agent>> getAssignedAgents(
            @PathVariable Long buyerId) {
        return ResponseEntity.ok(mappingService.getAgentsForBuyer(buyerId));
    }

    // 🔹 BUYER DASHBOARD
    @GetMapping("/buyer/{buyerId}")
    public List<BuyerAgentMapping> buyerMappings(
            @PathVariable Long buyerId) {
        return mappingService.getBuyerMappings(buyerId);
    }

    // 🔹 AGENT DASHBOARD (agentId)
    @GetMapping("/agent/{agentId}")
    public List<BuyerAgentMapping> agentMappings(
            @PathVariable Long agentId) {
        return mappingService.getAgentMappings(agentId);
    }

    // 🔹 AGENT DASHBOARD (userId) ✅ FRONTEND USES THIS
    @GetMapping("/agent/user/{userId}")
    public List<BuyerAgentMapping> agentDashboardByUserId(
            @PathVariable Long userId) {
        return mappingService.getMappingsByAgentUserId(userId);
    }

    // 🔹 ACTIVE DEALS
    @GetMapping("/active")
    public List<BuyerAgentMapping> activeMappings() {
        return mappingService.getActiveMappings();
    }
}
