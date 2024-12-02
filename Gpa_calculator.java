import java.util.ArrayList;

public class Gpa_calculator {
    // Method to calculate GPA (dummy implementation)
    public static double GPAequation(ArrayList<Integer> grades) {
        if (grades.isEmpty()) return 0.0;
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return total / (double) grades.size();
    }
}
