package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    private static List<Payment> paymentList = new ArrayList<>();
    private String bankName;
    private String borrowerName;
    private String lumpSumAmount;
    private String emiNumber;

    public static List<Payment> getPaymentList(Payment payment) {
        if (payment != null) {
            paymentList.add(payment);
        }
        return paymentList;
    }

    public BigDecimal getTotalLumpSumPaymentDoneTillAnEmiNumber(String bankName,String borrowerName,String emiNumber){
        BigDecimal totalLumpSum = BigDecimal.ZERO;
        if(paymentList.size()>0) {
            for (Payment payment : paymentList) {
                if (payment.bankName.equalsIgnoreCase(bankName)
                        && payment.borrowerName.equalsIgnoreCase(borrowerName) && Integer.parseInt(payment.emiNumber) <= Integer.parseInt(emiNumber)) {
                    totalLumpSum = totalLumpSum.add(BigDecimal.valueOf(Double.valueOf(payment.lumpSumAmount)));
                }
            }
        }
        return  totalLumpSum;
    }
}
