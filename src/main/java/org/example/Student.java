package org.example;

public class Student extends Person{
    protected String group;

    public Student(String name, String email, String group){
        super(name, email);
        this.group = group;
    }
    public String DisplayInformation(){
        // Call parent display method and display group information
        return (super.DisplayInformation() + String.format(", Group: %s", group));
    }
}
