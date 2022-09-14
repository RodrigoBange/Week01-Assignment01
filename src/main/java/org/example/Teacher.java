package org.example;

public class Teacher extends Person{
    double salary;

    public Teacher(String name, String email, double salary){
        super(name, email);
        this.salary = salary;
    }
    public String DisplayInformation(){
        // Call parent display method and display salary
        return (super.DisplayInformation() + String.format(", Salary: %s", salary));
    }
}
