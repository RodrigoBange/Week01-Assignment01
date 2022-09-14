package org.example;

public class Main {
    public static void main(String[] args) {
        // Create new Person, Student and Teacher
        Person person = new Person("Person", "person@gmail.com");
        Person student = new Student("Student", "student@gmail.com", "Group01");
        Person teacher = new Teacher("Teacher","teacher@gmail.com",1250.99);

        // Display all information
        System.out.println(person.DisplayInformation());
        System.out.println(student.DisplayInformation());
        System.out.println(teacher.DisplayInformation());
    }
}