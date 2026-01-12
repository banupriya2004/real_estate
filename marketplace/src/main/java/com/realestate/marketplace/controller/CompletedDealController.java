package com.realestate.marketplace.controller;

import com.realestate.marketplace.entity.BuyerAgentMapping;
import com.realestate.marketplace.service.CompletedDealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/completed-deals")
@CrossOrigin
public class CompletedDealController {

    private final CompletedDealService completedDealService;

    public CompletedDealController(CompletedDealService completedDealService) {
        this.completedDealService = completedDealService;
    }

    // 1️⃣ View completed (sold) deals
    @GetMapping
    public List<BuyerAgentMapping> getCompletedDeals() {
        return completedDealService.getCompletedDeals();
    }

    @PutMapping("/{id}/complete")
    public BuyerAgentMapping completeDeal(@PathVariable Long id) {
        return completedDealService.completeDeal(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCompletedDeal(@PathVariable Long id) {
        completedDealService.deleteCompletedDeal(id);
        return "Completed deal deleted successfully";
    }

}
