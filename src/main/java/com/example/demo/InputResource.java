package com.example.demo;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class InputResource {

    public void readFile(String filePath) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try  {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> inputList = Arrays.asList(line.split(" "));
                if (inputList.get(0).equalsIgnoreCase("LOAN")) {
                    Loan loan = Loan.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).principal(convertToDecimal(inputList.get(3))).noOfYearsOfLoan(convertToDecimal(inputList.get(4))).rateOfInterest(convertToDecimal(inputList.get(5))).build();
                    Loan.getLoanList(loan);
                } else if (inputList.get(0).equalsIgnoreCase("PAYMENT")) {
                    Loan.adjustLoanValues(Payment.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).lumpSumAmount(BigDecimal.valueOf(Double.valueOf(inputList.get(3)))).emiNumber(inputList.get(4)).build());
                    Payment.getPaymentList(Payment.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).lumpSumAmount(convertToDecimal(inputList.get(3))).emiNumber(inputList.get(4)).build());
                } else if (inputList.get(0).equalsIgnoreCase("BALANCE")) {
                    new Loan().calculateBalance(Balance.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).emiNumber(inputList.get(3)).build());
                }
            }
        }
        finally {
          br.close();
        }
    }

    private BigDecimal convertToDecimal(String str) {
        return BigDecimal.valueOf(Double.valueOf(str));
    }

}
