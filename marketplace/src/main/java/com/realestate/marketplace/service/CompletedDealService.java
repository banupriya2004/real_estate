package com.realestate.marketplace.service;

import com.realestate.marketplace.entity.Agent;
import com.realestate.marketplace.entity.BuyerAgentMapping;
import com.realestate.marketplace.repository.AgentRepository;
import com.realestate.marketplace.repository.BuyerAgentMappingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompletedDealService {

    private final BuyerAgentMappingRepository mappingRepository;
    private final AgentRepository agentRepository;

    public CompletedDealService(BuyerAgentMappingRepository mappingRepository,
                                AgentRepository agentRepository) {
        this.mappingRepository = mappingRepository;
        this.agentRepository = agentRepository;
    }

    // Get all completed deals
    public List<BuyerAgentMapping> getCompletedDeals() {
        return mappingRepository.findByActive(false);
    }

    // Mark deal as completed (sold)
    public BuyerAgentMapping completeDeal(Long mappingId) {
        BuyerAgentMapping mapping = mappingRepository.findById(mappingId)
                .orElseThrow(() -> new RuntimeException("Mapping not found"));

        mapping.setActive(false); // mark as completed

        // Make agent available again
        Agent agent = mapping.getAgent();
        agent.setAvailable(true);
        agentRepository.save(agent);

        return mappingRepository.save(mapping);
    }

    // Delete completed deal (admin only)
    public void deleteCompletedDeal(Long mappingId) {
        mappingRepository.deleteById(mappingId);
    }
}
