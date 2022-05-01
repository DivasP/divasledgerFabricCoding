package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    private static List<Loan> loanList = new ArrayList<>();
    private String bankName;
    private String borrowerName;
    private BigDecimal principal;
    private BigDecimal noOfYearsOfLoan;
    private BigDecimal rateOfInterest;
    private BigDecimal totalAmount;
    private BigDecimal interest;

    private BigDecimal numberOfEmis;

    private BigDecimal emiValue;
    private BigDecimal initialTotalAmount;

    public static List<Loan> getLoanList(Loan loan) {
        if (loan != null) {
            loanList.add(loan);
        }
        return loanList;
    }

    public static void adjustLoanValues(Payment payment) {
        for (Loan loan : loanList) {
            if (loan.getBankName().equalsIgnoreCase(payment.getBankName()) && loan.getBorrowerName().equalsIgnoreCase(payment.getBorrowerName())) {
                loan.setRemainingValues(payment.getLumpSumAmount(), payment.getEmiNumber());
            }
        }
    }

    public BigDecimal getTotalAmount() {
        return (this.getPrincipal()).add(this.getInterest());
    }

    public BigDecimal getInitialTotalAmount() {
        return (this.getPrincipal()).add(this.getInterest());
    }

    public BigDecimal getInterest() {
        BigDecimal interest = this.getPrincipal().multiply(this.getRateOfInterest()).multiply(this.getNoOfYearsOfLoan());
        return interest.divide(BigDecimal.valueOf(100));
    }

    public BigDecimal getEmiValue() {
        if (this.getTotalAmount().compareTo(this.getOriginalEmiValue()) == -1) {
            return this.getTotalAmount().setScale(0, RoundingMode.CEILING);
        } else {
            return getOriginalEmiValue().setScale(0, RoundingMode.CEILING);
        }
    }

    public BigDecimal getOriginalEmiValue() {
        return this.getInitialTotalAmount().divide((BigDecimal.valueOf(12).multiply(this.getNoOfYearsOfLoan())), 0, RoundingMode.CEILING);
    }

    public BigDecimal getNumberOfEmis() {
        return this.getTotalAmount().divide(this.getEmiValue(), RoundingMode.CEILING);
    }

    private void setRemainingValues(BigDecimal lumpSum, String emiNumber) {
        this.totalAmount = this.getTotalAmount().subtract(lumpSum).subtract(this.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(emiNumber))));
    }

    public void calculateBalance(Balance balance) {
        for (Loan loan : loanList) {
            if (loan.getBankName().equalsIgnoreCase(balance.getBankName()) && loan.getBorrowerName().equalsIgnoreCase(balance.getBorrowerName())) {

                balance.setAmountPaid(new Payment().getTotalLumpSumPaymentDoneTillAnEmiNumber(balance.getBankName(), balance.getBorrowerName(), balance.getEmiNumber()).add(loan.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(balance.getEmiNumber())))));
                BigDecimal amountRemaining = (loan.getInitialTotalAmount().subtract(new Payment().getTotalLumpSumPaymentDoneTillAnEmiNumber(balance.getBankName(), balance.getBorrowerName(), balance.getEmiNumber())).subtract(loan.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(balance.getEmiNumber())))));
                balance.setNumberOfEmisLeft(amountRemaining.divide(loan.getEmiValue(), 0, RoundingMode.CEILING));
                System.out.printf(balance + "\n");
            }
        }
    }

    @Override
    public String toString() {
        return "Loan object created";
    }
}
