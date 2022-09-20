package org.example;
import java.time.LocalDate;

public class Teacher extends Person{
    public Teacher(int id, String userName, String password, String role, String firstName, String lastName, LocalDate birthDate, int age){
        super(id, userName, password, role, firstName, lastName, birthDate, age);
    }
}
