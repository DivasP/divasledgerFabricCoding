package com.example.demo;

import lombok.*;

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
    private String principal;
    private String noOfYearsOfLoan;
    private String rateOfInterest;
    private BigDecimal totalAmount;
    private BigDecimal interest;

    private BigDecimal numberOfEmis;

    private BigDecimal emiValue;
    private BigDecimal initialTotalAmount;

    public BigDecimal getTotalAmount() {
        return ((BigDecimal.valueOf(Double.valueOf(this.principal)).add(this.getInterest())));
    }

    public BigDecimal getInitialTotalAmount() {
        return ((BigDecimal.valueOf(Double.valueOf(this.principal)).add(this.getInterest())));
    }


    public BigDecimal getInterest() {
        BigDecimal interest = BigDecimal.valueOf(Double.valueOf(this.getPrincipal())).multiply(BigDecimal.valueOf(Double.valueOf(this.getRateOfInterest()))).multiply(BigDecimal.valueOf(Double.valueOf(this.getNoOfYearsOfLoan())));
        return interest.divide(BigDecimal.valueOf(100));
    }

    public BigDecimal getEmiValue() {
        if (this.getTotalAmount().compareTo(this.getOriginalEmiValue()) == -1) {
            return this.getTotalAmount().setScale(0, RoundingMode.CEILING);
        } else {
             return getOriginalEmiValue();
        }
    }

    public BigDecimal getOriginalEmiValue(){
        return this.getInitialTotalAmount().divide(BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(Double.valueOf(this.getNoOfYearsOfLoan()))), RoundingMode.CEILING);
    }

    public BigDecimal getNumberOfEmis(){
        return this.getTotalAmount().divide(this.getEmiValue(),RoundingMode.CEILING);
    }

    public static List<Loan> getLoanList(Loan loan){
        if(loan!=null) {
            loanList.add(loan);
        }
     return loanList;
    }

    public static void adjustLoanValues(Payment payment){
        for (Loan loan: loanList) {
            if(loan.getBankName().equalsIgnoreCase(payment.getBankName()) && loan.getBorrowerName().equalsIgnoreCase(payment.getBorrowerName())){
                loan.setRemainingValues(payment.getLumpSumAmount(),payment.getEmiNumber());
            }
        }
    }

    private void setRemainingValues(String lumpSum, String emiNumber){
        this.totalAmount = this.getTotalAmount().subtract(BigDecimal.valueOf(Double.valueOf(lumpSum))).subtract(this.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(emiNumber))));
    }

    public void calculateBalance(Balance balance){
        for(Loan loan:loanList){
            if(loan.getBankName().equalsIgnoreCase(balance.getBankName())&&
            loan.getBorrowerName().equalsIgnoreCase(balance.getBorrowerName())){

                balance.setAmountPaid(new Payment().getTotalLumpSumPaymentDoneTillAnEmiNumber(balance.getBankName(),balance.getBorrowerName(),balance.getEmiNumber()).add(loan.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(balance.getEmiNumber())))));
                BigDecimal emiRemaining = (loan.getInitialTotalAmount().subtract(new Payment().getTotalLumpSumPaymentDoneTillAnEmiNumber(balance.getBankName(),balance.getBorrowerName(),balance.getEmiNumber())).subtract(loan.getEmiValue().multiply(BigDecimal.valueOf(Double.valueOf(balance.getEmiNumber())))));
                balance.setNumberOfEmisLeft(emiRemaining.divide(loan.getEmiValue(), RoundingMode.CEILING));
                System.out.printf(balance.toString()+"\n");
            }
        }
    }

    @Override
    public String toString() {
        return "Loan object created";
    }
}
