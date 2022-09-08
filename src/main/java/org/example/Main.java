package org.example;
import java.util.Scanner; // Import Scanner class

public class Main {
    public static void main(String[] args) {
        // Create a new Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Ask for group size
        System.out.println("Please enter the size of your group and press [ENTER]: ");
        int nrOfStudents = scanner.nextInt();

        // Display group size
        System.out.println("Group size: " + nrOfStudents);

        // Create array of Student objects
        Student[] students = new Student[nrOfStudents];

        // Ask for each student name and add to array
        for (int i = 0; i < nrOfStudents; i++){
            System.out.println("Please enter the name of student #" + (i+1) + " and press [ENTER]: ");
            String studentName = scanner.next();

            students[i] = new Student(studentName);
        }

        // Display all student names
        for (int i = 0; i < students.length; i++){
            System.out.println("Student #" + (i+1) + ": " + students[i].name);
        }

        // Ask for each student if present
        for (int i = 0; i < students.length; i++){
            System.out.println("Is Student #" + (i+1) + "(" + students[i].name + ") present? [Y/N + ENTER]: ");
            String answer = scanner.next();

            if (answer.equals("Y") || answer.equals("y")){
                students[i].present = true;
            }
            else if (answer.equals("N") || answer.equals("n")){
                students[i].present = false;
            }
        }

        // Display student presence
        for (int i = 0; i < students.length; i++){
            System.out.println("Student #" + (i+1) + ": " + students[i].name + "     | Present: " + students[i].present);
        }
    }
}