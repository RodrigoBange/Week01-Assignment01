package org.example;

public class Employee implements Payable{
    double salary;

    public Employee(double salary){
        this.salary = salary;
    }

    @Override
    public double GetPayout() {
        return (salary * 0.7);
    }
}
