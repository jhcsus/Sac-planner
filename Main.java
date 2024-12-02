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
                    System.out.println("\nAdvisors Available:");
                    // Assume we have advisors available to display
                    System.out.println("Advisors not yet implemented fully.");
                    break;

                case 3:
                    System.out.println("\n--- Semester Input Menu ---");
                    System.out.print("Enter the semester number: ");
                    int semesterNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the semester name: ");
                    String semesterName = scanner.nextLine();

                    Semester semester = new Semester(semesterNumber, semesterName);
                    boolean addingClasses = true;

                    while (addingClasses) {
                        System.out.println("\n--- Add Classes ---");
                        System.out.println("1. Add a class");
                        System.out.println("2. Finish semester");
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
                                semester.addGrade(grade);

                                // Check if it's a required class and pass it
                                for (int i = 0; i < degree.requiredClasses.size(); i++) {
                                    Class requiredClass = degree.requiredClasses.get(i);
                                    if (requiredClass.getClassName().equalsIgnoreCase(className)) {
                                        if (grade >= 60) { // Assuming 60 is the passing grade
                                            System.out.println("You passed " + requiredClass.getClassName() + "!");
                                            degree.requiredClasses.remove(i);
                                        }
                                        break;
                                    }
                                }
                                break;

                            case 2:
                                System.out.println("Finished entering classes for semester " + semesterName + ".");
                                semesters.add(semester);
                                addingClasses = false;
                                break;

                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n--- GPA Display ---");
                    for (Semester sem : semesters) {
                        System.out.println("GPA for " + sem.semesterName + ": " + sem.GPA());
                    }
                    break;

                case 5:
                    System.out.println("\nGoodbye, " + user.getUserName() + "!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

