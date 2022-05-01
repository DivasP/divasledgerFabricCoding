package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

class InputResourceTest {

    @Test
    public void readingInputFile1() throws IOException {
        String expectedData = "Hello, world!";
        InputResource inputResource = new InputResource();
        Class clazz = InputResource.class;
        InputStream inputStream = clazz.getResourceAsStream("/inputfile1.txt");
       // String data = inputResource.readFromInputStream(inputStream);
      //  System.out.println("The Data in the file is \n"+ data);
    }

    @Test
    public void readingInputFile2() throws IOException {
        String expectedData = "Hello, world!";
        InputResource inputResource = new InputResource();
        Class clazz = InputResource.class;
        InputStream inputStream = clazz.getResourceAsStream("/inputfile2.txt");
      //  String data = inputResource.readFromInputStream(inputStream);
      //  System.out.println("The Data in the file is \n"+ data);
    }

    @Test
    public void testGetInterest(){
        Loan loan = Loan.builder().principal("1000").rateOfInterest("10").noOfYearsOfLoan("5").build();
        System.out.println("The interest is " +loan.getInterest());
    }

}