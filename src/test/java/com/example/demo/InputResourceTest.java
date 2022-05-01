package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class InputResourceTest {
    @Test
    public void testGetInterest(){
        Loan loan = Loan.builder().principal(BigDecimal.valueOf(1000)).rateOfInterest(BigDecimal.TEN).noOfYearsOfLoan(BigDecimal.valueOf(5)).build();
        Assertions.assertEquals(BigDecimal.valueOf(500), loan.getInterest());
    }

}