package com.technokratos.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;
import java.util.Map;

public interface ContractService {
    Map<String, Object> createContract(Map<String, Object> contractName, BigDecimal balance) throws JsonProcessingException;
}
