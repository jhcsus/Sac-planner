import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create user
        System.out.println("Welcome to the Degree Planner!");
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        // Determine the degree
        System.out.println("Select your degree:");
        System.out.println("1. Computer Science");
        System.out.println("2. Mechanical Engineering");
        System.out.println("3. Business Administration");
        System.out.print("Enter your choice (1/2/3): ");
        int degreeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Degree degree = new Degree();
        switch (degreeChoice) {
            case 1:
                degree.addClass(new Class("Introduction to Programming", "B"));
                degree.addClass(new Class("Data Structures", "B"));
                degree.addClass(new Class("Operating Systems", "C"));
                break;
            case 2:
                degree.addClass(new Class("Statics", "B"));
                degree.addClass(new Class("Dynamics", "B"));
                degree.addClass(new Class("Thermodynamics", "C"));
                break;
            case 3:
                degree.addClass(new Class("Financial Accounting", "C"));
                degree.addClass(new Class("Microeconomics", "B"));
                degree.addClass(new Class("Marketing Principles", "C"));
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Computer Science.");
                degree.addClass(new Class("Introduction to Programming", "B"));
                degree.addClass(new Class("Data Structures", "B"));
                degree.addClass(new Class("Operating Systems", "C"));
                break;
        }

        ArrayList<Semester> semesters = new ArrayList<>();

        // Menu system
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display required classes");
            System.out.println("2. Talk to an advisor");
            System.out.println("3. Class input for the semester");
            System.out.println("4. Display GPA");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nRequired Classes:");
                    degree.listRequiredClasses();
                    break;

                case 2:
                    Advisor advisor = new Advisor();
                    advisor.addAdvisor("Dr. Smith", "smith@university.edu");
                    advisor.addAdvisor("Dr. Johnson", "johnson@university.edu");
                    advisor.addAdvisor("Dr. Lee", "lee@university.edu");

                    System.out.println("\n--- Advisor Menu ---");
                    System.out.println("1. Get suggested classes");
                    System.out.println("2. View advisor contact information");
                    System.out.print("Choose an option: ");
                    int advisorChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (advisorChoice == 1) {
                        System.out.println("\nClass Suggestions:");
                        advisor.suggestClasses(degree);
                    } else if (advisorChoice == 2) {
                        System.out.println("\nAdvisor Contact Information:");
                        advisor.displayAdvisors();
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Semester Input Menu ---");

                    // Updated semester number input
                    System.out.print("Enter the semester number: ");
                    String semesterNumberInput = scanner.nextLine(); // Read input as a string
                    int semesterNumber;
                    try {
                        semesterNumber = Integer.parseInt(semesterNumberInput); // Try parsing as an integer
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Defaulting to semester number 1.");
                        semesterNumber = 1; // Default value
                    }

                    System.out.print("Enter the semester name: ");
                    String semesterName = scanner.nextLine();

                    Semester semester = new Semester(semesterNumber, semesterName);
                    boolean addingClasses = true;

                    while (addingClasses) {
                        System.out.println("\n--- Add Classes ---");
                        System.out.println("1. Add a class");
                        System.out.println("2. Finish semester");
                        System.out.println("3. View previously entered semesters and their classes");
                        System.out.print("Choose an option: ");
                        int subChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (subChoice) {
                            case 1:
                                System.out.print("Enter the class name: ");
                                String className = scanner.nextLine();
                                System.out.print("Enter your grade for " + className + " (0-100): ");
                                int grade = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                semester.addGrade(className, grade);

                                // Check if it's a required class and pass it
                                for (int i = 0; i < degree.requiredClasses.size(); i++) {
                                    Class requiredClass = degree.requiredClasses.get(i);
                                    if (requiredClass.getClassName().equalsIgnoreCase(className)) {
                                        if (grade >= 60) { // Assuming 60 is the passing grade
                                            System.out.println("You passed " + requiredClass.getClassName() + "!");
                                            degree.requiredClasses.remove(i);
                                        } else {
                                            System.out.println("You failed " + requiredClass.getClassName() + "! Retake the class.");
                                        }
                                        break;
                                    }
                                }
                                break;

                            case 2:
                                semesters.add(semester);
                                addingClasses = false;
                                break;

                            case 3:
                                System.out.println("\nPreviously Entered Semesters:");
                                for (Semester sem : semesters) {
                                    System.out.println("Semester: " + sem.semesterName);
                                    System.out.println("Classes: " + sem.grades);
                                }
                                break;

                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }
                    }
                    break;


                case 4:
                    System.out.println("\n--- GPA Display ---");
                    System.out.println("1. Unweighted GPA");
                    System.out.println("2. Weighted GPA");
                    System.out.print("Choose an option: ");
                    int gpaChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (gpaChoice == 1) {
                        System.out.println("Your overall unweighted GPA: " +
                                semesters.stream()
                                        .mapToDouble(Semester::GPA)
                                        .average()
                                        .orElse(0.0));
                    } else if (gpaChoice == 2) {
                        System.out.println("Enter weights for each semester:");
                        ArrayList<Double> weights = new ArrayList<>();
                        for (Semester sem : semesters) {
                            System.out.println("Semester: " + sem.semesterName);
                            System.out.print("Weight: ");
                            weights.add(scanner.nextDouble());
                        }
                        double totalWeightedGPA = 0.0;
                        for (int i = 0; i < semesters.size(); i++) {
                            totalWeightedGPA += semesters.get(i).GPA() * weights.get(i);
                        }
                        double weightedGPA = totalWeightedGPA / weights.stream().mapToDouble(w -> w).sum();
                        System.out.println("Your overall weighted GPA: " + weightedGPA);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}
