import java.util.ArrayList;

public class Advisor {
    public ArrayList<String> advisorNames;

    // Constructor
    public Advisor() {
        advisorNames = new ArrayList<>();
    }

    // Add an advisor
    public void addAdvisor(String advisorName) {
        advisorNames.add(advisorName);
    }

    // Suggest classes (dummy implementation)
    public void suggestClasses(Degree degree) {
        System.out.println("Suggested Classes:");
        for (Class requiredClass : degree.requiredClasses) {
            System.out.println("- " + requiredClass.getClassName());
        }
    }
}
