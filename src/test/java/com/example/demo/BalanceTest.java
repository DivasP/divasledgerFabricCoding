package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BalanceTest {

    @Test
    public void checkTheBalanceOutputGenerated(){
        Balance balance = Balance.builder().
                bankName("TestBank").borrowerName("TestBorrower").amountPaid(BigDecimal.valueOf(10.5)).numberOfEmisLeft(BigDecimal.TEN).build();
        Assertions.assertEquals("TestBank TestBorrower 11 10", balance.toString());
    }
}
