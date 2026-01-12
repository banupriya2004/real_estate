package com.realestate.marketplace.repository;

import com.realestate.marketplace.dto.AgentDashboardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.marketplace.entity.BuyerAgentMapping;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BuyerAgentMappingRepository extends JpaRepository<BuyerAgentMapping, Long> {

    // Find all mappings for a buyer
    List<BuyerAgentMapping> findByBuyerId(Long buyerId);

    // Find all mappings for an agent
    List<BuyerAgentMapping> findByAgent_Id(Long agentId);

    List<BuyerAgentMapping> findByAgent_User_Id(Long userId);

    // Find all mappings by active status
    List<BuyerAgentMapping> findByActive(boolean active);

}
