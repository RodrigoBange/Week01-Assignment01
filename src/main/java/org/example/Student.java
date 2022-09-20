package org.example;
import java.time.LocalDate;

public class Student extends Person{
    public String group;

    public Student(int id, String userName, String password, String role, String firstName, String lastName, LocalDate birthDate, int age, String group){
        super(id, userName, password, role, firstName, lastName, birthDate, age);
        this.group = group;
    }
}
