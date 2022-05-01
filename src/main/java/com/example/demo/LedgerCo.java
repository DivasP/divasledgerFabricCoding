package com.example.demo;

import java.io.IOException;

public class LedgerCo {
    public static void main(String[] args) {
            String filePath = args[0];
        try {
            new InputResource().readFile(filePath);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
