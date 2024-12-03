import java.util.ArrayList;

public class Degree {
    ArrayList<Class> requiredClasses;

    // Constructor to initialize the requiredClasses list
    public Degree() {
        requiredClasses = new ArrayList<>();
        advisors = new ArrayList<>();
    }

    public void addClass(Class newClass) {
        requiredClasses.add(newClass);
    }

    public void listRequiredClasses() {
        for (Class c : requiredClasses) {
            System.out.println("Class Name: " + c.getClassName() + ", Required Grade: " + c.requiredGrade());
        }
    }
    public void listAdvisors(){
        //Littel edge case
        if (advisors.isEmpty()){
            System.out.println("No advisors!")
        }
        else{
            for(Advisor advisor : advisors){
                System.out.println("Advisors Nam:"+advisor.getName() + ",Email: " +advisor.getEmail());
            }
    }
}
}
