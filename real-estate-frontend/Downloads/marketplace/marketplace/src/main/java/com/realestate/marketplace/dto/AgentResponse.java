package com.realestate.marketplace.dto;

public class AgentResponse {

    private Long id;
    private String area;
    private int experience;
    private double rating;
    private String userName;   // user name
    private String userEmail;  // user email

    public AgentResponse(Long id, String area, int experience, double rating, String userName, String userEmail) {
        this.id = id;
        this.area = area;
        this.experience = experience;
        this.rating = rating;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}
