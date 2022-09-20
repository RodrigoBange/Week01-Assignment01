package org.example;
import java.time.LocalDate;

public class Person {
    protected int id;
    protected String userName;
    protected String password;
    protected String role;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected int age;

    // Constructor
    public Person(int id, String userName, String password, String role, String firstName, String lastName, LocalDate birthDate, int age){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
    }
}
