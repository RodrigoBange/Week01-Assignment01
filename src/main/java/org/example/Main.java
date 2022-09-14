package org.example;
import java.util.Scanner; // Import Scanner class

public class Main {
    public static void main(String[] args) {
        // Create a new Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Display course question
        System.out.println("Enter course name: ");

        // Save the user input to name
        String courseName = scanner.nextLine();

        // Display number of student question
        System.out.println("Enter number of students: ");

        // Save the user input to age
        int nrOfStudents = scanner.nextInt();

        // Create arrays for studentNames and studentGrades
        String[] studentNames = new String[nrOfStudents];
        int[] studentGrades = new int[nrOfStudents];

        // Ask for each students' name and save to array
        for (int i = 0; i < nrOfStudents; i++) {
            System.out.println("Enter name of student " + (i + 1) + ": ");
            String studentName = scanner.next();
            studentNames[i] = studentName;
        }

        // Ask for each students' grades and save to array
        for (int i = 0; i < nrOfStudents; i++) {
           System.out.println("Enter grade of " + studentNames[i] + ": ");
           studentGrades[i] = scanner.nextInt();
        }

        // Get the average grade of all grades
        double averageGrade = calculateAverageGrade(studentGrades);

        // Calculate the average of all student grades
        System.out.println("Average grade: " + String.format("%.1f", averageGrade));

        // Get student number with the highest grade and display result
        int maxGradeStudentId = findStudentWithMaxGrade(studentGrades);
        System.out.println("Student " + studentNames[maxGradeStudentId] + " has maximum grade: " + studentGrades[maxGradeStudentId]);

        // Display all results
        for (int i = 0; i < nrOfStudents; i++){
            System.out.println("Grade for student " + studentNames[i] + " (course " + courseName + "): " + studentGrades[i]);
        }
    }

    private static double calculateAverageGrade(int[] grades) {
        // Default value
        int sum = 0;

        // Add up all grades
        for (int grade : grades) {
            sum += grade;
        }

        // Return average of all grades
        return (double)sum / grades.length;
    }

    private static int findStudentWithMaxGrade(int[] grades) {
        // Default values
        int maximumGrade = 0;
        int studentId = 0;

        // Find the student number with the maximum grade
        for (int i = 0; i < grades.length; i++){
            if (grades[i] > maximumGrade){
                maximumGrade = grades[i];
                studentId = i;
            }
        }

        // Return id of student
        return studentId;
    }
}