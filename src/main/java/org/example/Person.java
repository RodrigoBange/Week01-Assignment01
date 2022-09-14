package org.example;

public class Person {
    protected String name;
    protected String email;

    // Constructor
    public Person(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String DisplayInformation(){
        // Display Person information
        return String.format("Name: %s, Email: %s", name, email);
    }
}
