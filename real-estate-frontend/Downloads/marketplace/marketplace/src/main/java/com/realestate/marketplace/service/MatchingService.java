package com.realestate.marketplace.service;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class MatchingService {

    private final AgentRepository agentRepository;

    public MatchingService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent matchBestAgent(String area) {
        return agentRepository.findByArea(area).stream()
                .sorted(
                        Comparator.comparingDouble(Agent::getRating)
                                .thenComparing(Agent::getExperience)
                                .reversed()
                )
                .findFirst()
                .orElse(null);
    }
}
