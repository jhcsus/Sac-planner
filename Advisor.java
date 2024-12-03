import java.util.ArrayList;

public class Advisor {
    private String name;
    private String email;

    // Constructor
    public Advisor(String name, String email) {
        this.name = name;
        this.emaiil = email;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
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
