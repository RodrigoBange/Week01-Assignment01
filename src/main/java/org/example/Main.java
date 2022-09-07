package org.example;
import java.util.Scanner; // Import Scanner class

public class Main {
    public static void main(String[] args) {
        // Create a new Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Display name question
        System.out.println("Enter your name: ");

        // Save the user input to name
        String name = scanner.nextLine();

        // Display age question
        System.out.println("Enter your age: ");

        // Save the user input to age
        int age = scanner.nextInt();

        // Display final result
        System.out.println("Your name is: " + name + ". " + "Your age is: " + age + ".");
    }
}