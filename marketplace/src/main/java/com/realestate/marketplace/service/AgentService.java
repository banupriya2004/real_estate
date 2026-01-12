package com.realestate.marketplace.service;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id " + id));
    }

    public Agent updateAgent(Long id, Agent updatedAgent) {
        Agent existingAgent = getAgentById(id);
        existingAgent.setLocation(updatedAgent.getLocation()); // ✅ fixed
        existingAgent.setExperience(updatedAgent.getExperience());
        existingAgent.setRating(updatedAgent.getRating());

        return agentRepository.save(existingAgent);
    }

    public List<Agent> getAgentsByLocation(String location) {
        return agentRepository.findByLocationIgnoreCase(location);
    }

    public boolean deleteAgentById(Long agentId) {
        if (!agentRepository.existsById(agentId)) {
            return false;
        }
        agentRepository.deleteById(agentId);
        return true;
    }
}
