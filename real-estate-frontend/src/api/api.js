import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

export const api = {
  getProperties: () =>
    fetch(`${BASE_URL}/properties`).then(res => res.json()),

  mapAgent: (buyerId, propertyId) =>
    fetch(`${BASE_URL}/map-agent`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ buyerId, propertyId })
    }).then(res => res.json()),

  buyerProperties: (buyerId) =>
    fetch(`${BASE_URL}/buyer-properties?buyerId=${buyerId}`)
      .then(res => res.json()),

  agentProperties: (agentId) =>
    fetch(`${BASE_URL}/agent-properties?agentId=${agentId}`)
      .then(res => res.json()),

  completeDeal: (mappingId) =>
    fetch(`${BASE_URL}/complete-deal`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ mappingId })
    }),

  submitReview: (data) =>
    fetch(`${BASE_URL}/submit-review`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    }),

  completedDeals: () =>
    fetch(`${BASE_URL}/completed-deals`)
      .then(res => res.json())
};

const API_URL = "http://localhost:8080/api/mappings";

export const getMappedPropertiesForAgent = (agentUserId) => {
  return axios.get(`${API_URL}/agent/user/${agentUserId}`);
};


export default api;
