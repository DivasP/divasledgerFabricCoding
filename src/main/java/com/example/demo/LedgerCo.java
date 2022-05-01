package com.example.demo;

import java.io.IOException;
import java.io.InputStream;


public class LedgerCo {
    public static void main(String[] args) {
        try {
            Class clazz = InputResource.class;
            InputStream inputStream = clazz.getResourceAsStream("/"+args[0]);
            //InputResource.class.getClass().getResourceAsStream(args[0])
            InputResource.readFromInputStream(inputStream);
        } catch (IOException e) {
            System.out.println("Error while reading the file" + e);
        }
    }
}
