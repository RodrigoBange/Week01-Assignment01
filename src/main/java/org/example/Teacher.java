package org.example;
import java.util.Date;

public class Teacher extends Person{
    public Teacher(int id, String userName, String password, String role, String firstName, String lastName, Date birthDate, int age){
        super(id, userName, password, role, firstName, lastName, birthDate, age);
    }
}
