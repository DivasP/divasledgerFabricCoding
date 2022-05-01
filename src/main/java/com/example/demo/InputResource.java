package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputResource {

	public static void readFromInputStream(InputStream inputStream)
			throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br
					 = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				List<String> inputList = Arrays.asList(line.split(" "));
				if(inputList.get(0).equalsIgnoreCase("LOAN")){
					Loan loan = Loan.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).principal(inputList.get(3)).noOfYearsOfLoan(inputList.get(4)).rateOfInterest(inputList.get(5)).build();
					Loan.getLoanList(loan);
				} else if (inputList.get(0).equalsIgnoreCase("PAYMENT")) {
					Loan.adjustLoanValues(Payment.builder()
									.bankName(inputList.get(1))
									.borrowerName(inputList.get(2))
									.lumpSumAmount(inputList.get(3))
									.emiNumber(inputList.get(4))
							.build());
					Payment.getPaymentList(Payment.builder().bankName(inputList.get(1)).borrowerName(inputList.get(2)).lumpSumAmount(inputList.get(3)).emiNumber(inputList.get(4)).build());
				} else if (inputList.get(0).equalsIgnoreCase("BALANCE")){
					new Loan().calculateBalance(Balance.builder()
									.bankName(inputList.get(1)).borrowerName(inputList.get(2))
									.emiNumber(inputList.get(3))
							.build());
				}
			//	resultStringBuilder.append(line).append("\n");
			}
		}
		//return resultStringBuilder.toString();
	}

}
