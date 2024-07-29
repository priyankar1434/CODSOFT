import java.util.ArrayList;
import java.util.Scanner;

// Step 1: Create a class to represent the Course
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    public boolean dropStudent(Student student) {
        return registeredStudents.remove(student);
    }

    public boolean isStudentRegistered(Student student) {
        return registeredStudents.contains(student);
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + getAvailableSlots());
        System.out.println();
    }
}

// Step 1: Create a class to represent the Student
class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        if (course.registerStudent(this)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for course: " + course.getTitle());
        } else {
            System.out.println("Registration failed. Course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (course.dropStudent(this)) {
            registeredCourses.remove(course);
            System.out.println("Successfully dropped course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + ":");
        for (Course course : registeredCourses) {
            System.out.println(" - " + course.getTitle());
        }
        System.out.println();
    }
}

// Main class to run the program
public class CourseRegistrationSystem {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();

    // Method to display available courses
    public static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            course.displayCourseDetails();
        }
    }

    // Method to find a course by its code
    public static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Method to find a student by their ID
    public static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding some sample courses
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of Computer Science", 30, "MWF 10-11 AM"));
        courses.add(new Course("MAT201", "Calculus I", "Introduction to Calculus", 25, "TTh 9-10:30 AM"));
        courses.add(new Course("PHY101", "Physics I", "Fundamentals of Physics", 20, "MWF 1-2 PM"));

        // Adding some sample students
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));

        // Main menu loop
        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter Course Code to Register: ");
                        String courseCode = scanner.nextLine();
                        Course course = findCourse(courseCode);
                        if (course != null) {
                            student.registerCourse(course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter Course Code to Drop: ");
                        courseCode = scanner.nextLine();
                        Course course = findCourse(courseCode);
                        if (course != null) {
                            student.dropCourse(course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudent(studentID);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
