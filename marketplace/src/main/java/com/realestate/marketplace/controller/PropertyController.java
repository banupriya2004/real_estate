package com.realestate.marketplace.controller;

import com.realestate.marketplace.dto.PropertyRequest;
import com.realestate.marketplace.entity.Property;
import com.realestate.marketplace.service.PropertyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
//@RequestMapping("/api/properties")
//@CrossOrigin("*")
//public class PropertyController {
//
//    private final PropertyService propertyService;
//
//    public PropertyController(PropertyService propertyService) {
//        this.propertyService = propertyService;
//    }
//
//    @PostMapping(
//            value = "/add",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
//    )
//    public Property addProperty(
//            @RequestParam String title,
//            @RequestParam String location,
//            @RequestParam double price,
//            @RequestParam String type,
//            @RequestParam String description,
//            @RequestParam Long agentId,
//            @RequestPart MultipartFile image
//    ) {
//        PropertyRequest request = new PropertyRequest();
//        request.setTitle(title);
//        request.setLocation(location);
//        request.setPrice(price);
//        request.setType(type);
//        request.setDescription(description);
//        request.setAgentId(agentId);
//        request.setImage(image);
//
//        return propertyService.addProperty(request);
//    }
//
//    @GetMapping
//    public List<Property> getAllProperties() {
//        return propertyService.getAllProperties();
//    }
//
//    @GetMapping("/search")
//    public List<Property> search(@RequestParam String location) {
//        return propertyService.searchByLocation(location);
//    }
//
//    // ✅ GET single property by ID
//    @GetMapping("/{id}")
//    public Property getPropertyById(@PathVariable Long id) {
//        return propertyService.getPropertyById(id);
//    }
//
//
//    @PutMapping("/{id}")
//    public Property updateProperty(
//            @PathVariable Long id,
//            @RequestBody Property updatedProperty
//    ) {
//        return propertyService.updateProperty(id, updatedProperty);
//    }
//
//    // ✅ DELETE PROPERTY
//    @DeleteMapping("/{id}")
//    public String deleteProperty(@PathVariable Long id) {
//        boolean deleted = propertyService.deleteProperty(id);
//        return deleted ? "Property deleted" : "Property not found";
//    }
//}






@RestController
@RequestMapping("/api/properties")
@CrossOrigin("*")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // ✅ FIXED: Using @ModelAttribute handles Form Data + Files automatically.
    // It also allows 'agentId' to be null without crashing.
    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Property addProperty(@ModelAttribute PropertyRequest request) {
        return propertyService.addProperty(request);
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/search")
    public List<Property> search(@RequestParam String location) {
        return propertyService.searchByLocation(location);
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public Property updateProperty(
            @PathVariable Long id,
            @RequestBody Property updatedProperty
    ) {
        return propertyService.updateProperty(id, updatedProperty);
    }

    @DeleteMapping("/{id}")
    public String deleteProperty(@PathVariable Long id) {
        boolean deleted = propertyService.deleteProperty(id);
        return deleted ? "Property deleted" : "Property not found";
    }
}