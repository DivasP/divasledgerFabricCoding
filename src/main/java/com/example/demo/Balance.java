package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balance {
    private String bankName;
    private String borrowerName;
    private String emiNumber;

    @Override
    public String toString() {
        return this.bankName+" "+this.borrowerName+" "+this.amountPaid+" "+this.numberOfEmisLeft;
    }

    private BigDecimal amountPaid;
    private BigDecimal numberOfEmisLeft;


}
