package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize list of Payable interfaces
        List<Payable> payables = new ArrayList<>();

        // Employee with a 3000 salary
        Employee employee = new Employee(3000);
        // Freelancer with 140 worked hours and 60 hour-rate
        Freelancer freelancer = new Freelancer(140, 60);

        // Add workers to the list
        payables.add(employee);
        payables.add(freelancer);

        // Display worker payouts
        for (Payable payable: payables) {
            System.out.printf("%s earns: %s %n", payable.getClass().getSimpleName() ,payable.GetPayout());
        }
    }
}