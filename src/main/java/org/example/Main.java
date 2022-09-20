package org.example;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.FileReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import static java.lang.Integer.parseInt;

public class Main {
    private static final String FILE_LOCATION = "src/main/java/org/example/Students_Teachers.csv";
    private static final String STUDENT_ROLE = "Student";
    private static final String TEACHER_ROLE = "Teacher";
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withLocale(Locale.ENGLISH);
    private static List<Person> people;

    public static void main(String[] args) {
        // Get all university people
        people = getPeopleFromFile();

        // If file found...
        if (!people.isEmpty()){
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
                userRole = getUserInfo(userName, password);
            }
            while(userRole == null);

            // Enable dashboard
            enableDashboard(userRole);
        }
    }

    private static List<Person> getPeopleFromFile(){
        List<String[]> entriesList;

        // Instantiate CSVReader and read all lines of CSV file
        try (CSVReader reader = new CSVReader(new FileReader(FILE_LOCATION))){
            entriesList = reader.readAll();
        }
        catch (Exception ex){
            // If file not found, display error and return empty array
            System.out.println("File not found!");
            return Collections.emptyList();
        }

        // Create a Person list
        List<Person> tempPeople = new ArrayList<>();

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
            LocalDate birthDate = null;
            try {
                birthDate = LocalDate.parse(entriesStrings[6], formatter);
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
                Person student = new Student(id, userName, password, role, firstName, lastName, birthDate, age, group);
                tempPeople.add(student);
            }
            else if (role.equals(TEACHER_ROLE)){
                // Create new Teacher
                Person teacher = new Teacher(id, userName, password, role, firstName, lastName, birthDate, age);
                tempPeople.add(teacher);
            }
        }

        // Return array of people
        return tempPeople;
    }

    private static String getUserInfo(String enteredUsername, String enteredPassword){
        // If array contains username and password combination...
        for (Person user : people) {
            if (user.userName.equalsIgnoreCase(enteredUsername) && user.password.equals(enteredPassword)) {
                // Display success and return true
                System.out.println("Login successful!");
                return user.role;
            }
        }

        // Display error and return false
        System.out.println("Incorrect information / User not found. Please try again.");
        return null;
    }

    private static void enableDashboard(String userRole){
        // Display role commands
        if (userRole.equals(STUDENT_ROLE)){
            enableStudentCommands();
        }
        else if (userRole.equals(TEACHER_ROLE)){
            enableTeacherCommands();
        }
    }

    private static void enableStudentCommands(){
        String userCommand;

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Ask for user commands till exiting
        do {
            System.out.println("S. Display Students | T. Display Teachers | X. exit |");
            System.out.println("Please enter a choice: ");
            userCommand = scanner.next();

            // Command options
            if (userCommand.equalsIgnoreCase("S")){
                displayStudents();
            }
            else if (userCommand.equalsIgnoreCase("T")){
                displayTeachers();
            }
            else if (!userCommand.equalsIgnoreCase("X")){
                System.out.println("Command does not exist.");
            }
        }
        while(!userCommand.equalsIgnoreCase("X"));
    }

    private static void enableTeacherCommands(){
        String userCommand;

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Ask for user commands till exiting
        do {
            System.out.println("S. Display Students | T. Display Teachers | A. Add Students | R. Display Reports | X. exit |");
            System.out.println("Please enter a choice: ");
            userCommand = scanner.next();

            // Command options
            if (userCommand.equalsIgnoreCase("S")){
                displayStudents();
            }
            else if (userCommand.equalsIgnoreCase("T")){
                displayTeachers();
            }
            else if (userCommand.equalsIgnoreCase("A")){
                addStudent();
            }
            else if (!userCommand.equalsIgnoreCase("X")){
                System.out.println("Command does not exist.");
            }
        }
        while(!userCommand.equalsIgnoreCase("X"));
    }

    private static void displayStudents(){
        List<Person> students = new ArrayList<>();

        // Get all students from people array
        for (Person student : people) {
            if (student.role.equals(STUDENT_ROLE)) {
                students.add(student);
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
        System.out.printf("%-5s %15s %15s %15s %10s %13s %n", headerId, headerFirstName, headerLastName, headerBirthDate, headerAge, headerGroup);
        System.out.println(divider);

        for (Person student : students) {
            // Convert Date to correct format
            String birthDate = formatter.format(student.birthDate);
            System.out.printf("%-5s %15s %15s %15s %10s %15s %n", student.id, student.firstName, student.lastName, birthDate, student.age, ((Student)student).group);
        }
    }

    private static void displayTeachers() {
        List<Person> teachers = new ArrayList<>();

        // Get all teachers from people array
        for (Person teacher : people) {
            if (teacher.role.equals(TEACHER_ROLE)) {
                teachers.add(teacher);
            }
        }

        // Display title
        System.out.println("LIST OF TEACHERS");

        // Display header tags
        String headerId = "Id";
        String headerFirstName = "FirstName";
        String headerLastName = "LastName";
        String headerBirthDate = "BirthDate";
        String headerAge = "Age";
        String divider = "-----------------------------------------------------------------------------------";

        // Display header
        System.out.printf("%-5s %15s %15s %15s %10s %n", headerId, headerFirstName, headerLastName, headerBirthDate, headerAge);
        System.out.println(divider);

        for (Person teacher : teachers) {
            // Convert Date to correct format
            String birthDate = formatter.format(teacher.birthDate);
            System.out.printf("%-5s %15s %15s %15s %10s %n", teacher.id, teacher.firstName, teacher.lastName, birthDate, teacher.age);
        }
    }

    private static void addStudent(){
        // Student variables
        String userName = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String birthDate = "";
        LocalDate currentDate = LocalDate.now();
        LocalDate convertedBirthDate = LocalDate.now();
        int age = 0;
        String group = "";

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Ask for student information...
        while (userName.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                birthDate.isEmpty() || group.isEmpty()){
            // Header
            System.out.println("ADD A STUDENT");
            // Ask for student information
            System.out.println("Choose an username: ");
            userName = scanner.next();
            System.out.println("Choose a password: ");
            password = scanner.next();
            System.out.println("Enter first name: ");
            firstName = scanner.next();
            System.out.println("Enter last name: ");
            lastName = scanner.next();
            System.out.println("Please enter date of birth in MM/DD/YYYY: ");
            birthDate = scanner.next();
            System.out.println("Enter group: ");
            group = scanner.next();

            // Display error if any field is empty
            if (userName.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
                    birthDate.isEmpty() || group.isEmpty()) {
                System.out.println("Please fill in all the information correctly");
            }
        }

        // If correct, calculate age
        try {
            convertedBirthDate = LocalDate.parse(birthDate, formatter);
            age = Period.between(convertedBirthDate, currentDate).getYears();
        }
        catch (Exception ex){
            System.out.println("Date format is incorrect.");
        }

        // Get id from the highest id + 1 out of people array (-2 Due to the blank line added by OPENCSV)
        Person lastPerson = people.get(people.size() - 1);
        int personID = lastPerson.id + 1;

        // Create new Student
        Person student = new Student(personID, userName, password, STUDENT_ROLE, firstName, lastName, convertedBirthDate, age, group);

        // Try writing to file
        if (writeStudentToFile(student)){
            // Add student to list
            people.add(student);

            // Display success message
            System.out.println("The data was successfully added!");
        }
        else {
            // Display failure message
            System.out.println("The data was not successfully added.");
        }
    }

    private static boolean writeStudentToFile(Person student){
        // Convert Student variables to String[]
        String values;

        // Convert Date to correct format before continuing
        String birthDate = formatter.format(student.birthDate);

        values = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", student.id, student.userName, student.password, student.role,
                student.firstName, student.lastName, birthDate, student.age, ((Student)student).group);
        String[] valueLine = values.split(",");

        // Create new File writer, if it exists, append to it
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_LOCATION, true))){
            // Write new line of values
            writer.writeNext(valueLine);
            // Return success
            return true;
        }
        catch (Exception ex){
            // If file not found, display error and return empty array
            System.out.println("Something went wrong writing to the file!");
            // Return failure
            return false;
        }
    }
}