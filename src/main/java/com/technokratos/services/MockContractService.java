package com.technokratos.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MockContractService implements ContractService {

    @Override
    public Map<String, Object> createContract(Map<String, Object> contractName, BigDecimal balance) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        response.put("contractName", contractName);
        response.put("balance", balance);
        response.put("contractId", UUID.randomUUID().toString());
        response.put("status", "ACTIVE");
        response.put("createdAt", "2024-01-15T10:30:00Z");
        response.put("message", "success");
        return response;
    }
}
