package com.realestate.marketplace.service;

import com.realestate.marketplace.entity.*;
import com.realestate.marketplace.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuyerAgentMappingService {

    private final AgentRepository agentRepository;
    private final PropertyRepository propertyRepository;
    private final BuyerAgentMappingRepository mappingRepository;
    private final UserRepository userRepository;

    public BuyerAgentMappingService(
            AgentRepository agentRepository,
            PropertyRepository propertyRepository,
            BuyerAgentMappingRepository mappingRepository,
            UserRepository userRepository) {
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.mappingRepository = mappingRepository;
        this.userRepository = userRepository;
    }

    // 🔹 ASSIGN AGENT (Permanent Logic Fix)
    public BuyerAgentMapping assignAgent(Long buyerId, Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!userRepository.existsById(buyerId)) {
            throw new RuntimeException("Buyer not found");
        }

        String location = property.getLocation();
        List<Agent> agents = agentRepository.findByLocationIgnoreCase(location);

        if (agents.isEmpty()) throw new RuntimeException("No agents in this location");

        // Sort by rating
        agents.sort((a1, a2) -> Double.compare(a2.getRating(), a1.getRating()));

        Agent selectedAgent = null;
        for (Agent agent : agents) {
            if (agent.isAvailable()) { // ✅ Checks for TRUE (Available)
                selectedAgent = agent;
                break;
            }
        }

        if (selectedAgent == null) throw new RuntimeException("All agents are busy");

        selectedAgent.setAvailable(false); // ✅ Marks as BUSY
        agentRepository.save(selectedAgent);

        property.setAgent(selectedAgent);
        propertyRepository.save(property);

        BuyerAgentMapping mapping = new BuyerAgentMapping();
        mapping.setBuyerId(buyerId);
        mapping.setAgent(selectedAgent);
        mapping.setProperty(property);
        mapping.setLocation(location);
        mapping.setStatus(BuyerAgentMapping.Status.ASSIGNED);
        mapping.setAssignedAt(LocalDateTime.now());
        mapping.setActive(true);

        return mappingRepository.save(mapping);
    }

    // 🔹 AGENT DASHBOARD (Auto-fills Names)
    public List<BuyerAgentMapping> getMappingsByAgentUserId(Long userId) {
        List<BuyerAgentMapping> mappings = mappingRepository.findByAgent_User_Id(userId);

        for (BuyerAgentMapping mapping : mappings) {
            userRepository.findById(mapping.getBuyerId()).ifPresent(user -> {
                mapping.setBuyerName(user.getName());
                mapping.setBuyerEmail(user.getEmail());
                mapping.setBuyerPhone(user.getMobileNumber());
            });
        }
        return mappings;
    }

    // ... Keep your other standard getters (getAgentsForBuyer, etc.) ...
    public List<Agent> getAgentsForBuyer(Long buyerId) {
        List<BuyerAgentMapping> mappings = mappingRepository.findByBuyerId(buyerId);
        return mappings.stream().map(BuyerAgentMapping::getAgent).collect(Collectors.toList());
    }

    public List<BuyerAgentMapping> getBuyerMappings(Long buyerId) {
        return mappingRepository.findByBuyerId(buyerId);
    }

    public List<BuyerAgentMapping> getAgentMappings(Long agentId) {
        return mappingRepository.findByAgent_Id(agentId);
    }

    public List<BuyerAgentMapping> getActiveMappings() {
        return mappingRepository.findByActive(true);
    }
}