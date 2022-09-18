package org.example;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileReader;
import com.opencsv.CSVReader;

import static java.lang.Integer.parseInt;

public class Main {
    private static final String STUDENT_ROLE = "Student";
    private static final String TEACHER_ROLE = "Teacher";

    public static void main(String[] args) {
        // Get all university people
        Person[] people = getPeopleFromFile();

        // If file found...
        if (people.length > 0){
            // Username and password variables
            String userName;
            String password;
            String userRole;

            // Initialize Scanner
            Scanner scanner = new Scanner(System.in);

            // Ask for user information, repeat till successful login
            do {
                // Ask for username
                System.out.println("Enter username: ");
                userName = scanner.next();

                // Ask for password
                System.out.println("Enter password: ");
                password = scanner.next();

                // Get user information
                userRole = getUserInfo(people, userName, password);
            }
            while(userRole.equals(""));

            // Enable dashboard
            enableDashboard(userRole, people);
        }
    }

    private static Person[] getPeopleFromFile(){
        String fileLocation = "src/main/java/org/example/Students_Teachers.csv";
        List<String[]> entriesList;

        // Instantiate CSVReader and read all lines of CSV file
        try (CSVReader reader = new CSVReader(new FileReader(fileLocation))){
            entriesList = reader.readAll();
        }
        catch (Exception ex){
            // If file not found, display error and return empty array
            System.out.println("File not found!");
            return new Person[0];
        }

        // Create a Person array with the length of list entries
        Person[] people = new Person[entriesList.size() - 1];

        // For each string array in the entries list... (Starts at 1 to avoid headers)
        for (int i = 1; i < entriesList.size(); i++){
            String[] entriesStrings = entriesList.get(i);

            // Set data to variables
            int id = parseInt(entriesStrings[0]);
            String userName = entriesStrings[1];
            String password = entriesStrings[2];
            String role = entriesStrings[3];
            String firstName = entriesStrings[4];
            String lastName = entriesStrings[5];
            Date birthDate = new Date();
            try {
                birthDate = new SimpleDateFormat("MM/dd/yyyy", Locale.UK).parse(entriesStrings[6]);
            }
            catch (Exception ex){
                System.out.println("Date format is incorrect.");
            }
            int age = parseInt(entriesStrings[7]);

            // If role equals...
            if (role.equals(STUDENT_ROLE)){
                // Get the group value
                String group = entriesStrings[8];

                // Create new Student
                Person person = new Student(id, userName, password, role, firstName, lastName, birthDate, age, group);
                people[i - 1] = person;
                System.out.println(person.g);
            }
            else if (role.equals(TEACHER_ROLE)){
                // Create new Teacher
                Person person = new Teacher(id, userName, password, role, firstName, lastName, birthDate, age);
                people[i - 1] = person;
            }
        }

        // Return array of people
        return people;
    }

    private static String getUserInfo(Person[] users, String enteredUsername, String enteredPassword){
        // If array contains username and password combination...
        for (Person user : users) {
            if (user.userName.equals(enteredUsername) && user.password.equals(enteredPassword)) {
                // Display success and return true
                System.out.println("Login successful!");
                return user.role;
            }
        }

        // Display error and return false
        System.out.println("Incorrect information / User not found. Please try again.");
        return "";
    }

    private static void enableDashboard(String userRole, Person[] people){
        String userCommand = "";

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Display role commands
        if (userRole.equals(STUDENT_ROLE)){
            // Ask for user commands till exiting
            do {
                System.out.println("S. Display Students | T. Display Teachers | X. exit |");
                System.out.println("Please enter a choice: ");
                userCommand = scanner.next();

                // Command options
                if (userCommand.equalsIgnoreCase("S")){
                    displayStudents(people);
                }

            }
            while(!userCommand.equalsIgnoreCase("X"));

        }
        else if (userRole.equals(TEACHER_ROLE)){
            System.out.println("S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. exit |");
        }
    }

    private static void displayStudents(Person[] people){
        List<Person> students = new ArrayList<>();

        // Get all students from people array
        for (Person person : people) {
            if (person.role.equals(STUDENT_ROLE)) {
                students.add(person);
            }
        }

        // Display title
        System.out.println("LIST OF STUDENTS");

        // Display header tags
        String headerId = "Id";
        String headerFirstName = "FirstName";
        String headerLastName = "LastName";
        String headerBirthDate = "BirthDate";
        String headerAge = "Age";
        String headerGroup = "Group";
        String divider = "-----------------------------------------------------------------------------------";

        // Display header
        System.out.printf("%-5s %15s %15s %15s %10s %10s %n", headerId, headerFirstName, headerLastName, headerBirthDate, headerAge, headerGroup);
        System.out.println(divider);

        for (Person student : students) {
            // Convert Date to only display the date
            DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.UK);
            String birthDate = outputFormatter.format(student.birthDate);
            System.out.printf("%-5s %15s %15s %15s %10s %10s %n", student.id, student.firstName, student.lastName, birthDate, student.age, student.age);
        }
    }
}