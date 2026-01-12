package com.realestate.marketplace.dto;

public class AgentDashboardDTO {

    private Long mappingId;
    private Long buyerId;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String propertyTitle;
    private String status;

    public AgentDashboardDTO(
            Long mappingId,
            Long buyerId,
            String buyerName,
            String buyerEmail,
            String buyerPhone,
            String propertyTitle,
            String status
    ) {
        this.mappingId = mappingId;
        this.buyerId = buyerId;
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerPhone = buyerPhone;
        this.propertyTitle = propertyTitle;
        this.status = status;
    }

    public Long getMappingId() { return mappingId; }
    public Long getBuyerId() { return buyerId; }
    public String getBuyerName() { return buyerName; }
    public String getBuyerEmail() { return buyerEmail; }
    public String getBuyerPhone() { return buyerPhone; }
    public String getPropertyTitle() { return propertyTitle; }
    public String getStatus() { return status; }
}
