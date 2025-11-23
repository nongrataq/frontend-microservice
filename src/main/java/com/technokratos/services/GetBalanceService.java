package com.technokratos.services;

import java.util.Map;

public interface GetBalanceService {
    Map<String, Object> getBalance(String contractName);
}
