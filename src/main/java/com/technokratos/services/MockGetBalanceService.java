package com.technokratos.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MockGetBalanceService implements GetBalanceService {

    @Override
    public Map<String, Object> getBalance(String contractName) {
        Map<String, Object> response = new HashMap<>();
        response.put("contractName", contractName);
        response.put("balance", new BigDecimal("75000.25"));
        response.put("currency", "RUB");
        response.put("message", "success");
        response.put("lastUpdated", "2024-01-15T10:30:00Z");
        return response;
    }
}
