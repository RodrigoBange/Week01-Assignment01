package org.example;
import java.util.Date;

public class Student extends Person{
    String group;

    public Student(int id, String userName, String password, String role, String firstName, String lastName, Date birthDate, int age, String group){
        super(id, userName, password, role, firstName, lastName, birthDate, age);
        this.group = group;
    }
}
