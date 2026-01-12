package com.realestate.marketplace.repository;

import com.realestate.marketplace.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByLocation(String location);

    List<Property> findByLocationContainingIgnoreCase(String location);
}
