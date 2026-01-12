package com.realestate.marketplace.repository;

import com.realestate.marketplace.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findByArea(String area);
}
