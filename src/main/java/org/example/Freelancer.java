package org.example;

public class Freelancer implements Payable{
    int workedHours;
    double hourlyRate;

    public Freelancer(int workedHours, double hourlyRate){
        this.workedHours = workedHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double GetPayout() {
        return workedHours * hourlyRate;
    }
}
