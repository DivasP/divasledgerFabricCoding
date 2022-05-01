package com.example.demo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balance {
    private String bankName;
    private String borrowerName;
    private String emiNumber;
    private BigDecimal amountPaid;
    private BigDecimal numberOfEmisLeft;

    @Override
    public String toString() {
        return this.bankName + " " + this.borrowerName + " " + this.getAmountPaid() + " " + this.getNumberOfEmisLeft();
    }

    public BigDecimal getAmountPaid() {
        return this.amountPaid.setScale(0, RoundingMode.CEILING);
    }

    public BigDecimal getNumberOfEmisLeft() {
        return this.numberOfEmisLeft.setScale(0, RoundingMode.CEILING);
    }


}
