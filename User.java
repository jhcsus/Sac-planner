import java.util.Scanner;

public class User {
    public String userName;

    // Constructor
    public User(String userName) {
        this.userName = userName;
    }

    // Getter for userName
    public String getUserName() {
        return userName;
    }

    // Setter for userName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Static method to take user input
    public static User createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        return new User(name);
    }
}
