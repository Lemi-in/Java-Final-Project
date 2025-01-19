import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(10000.00, "1234"); // Initialize account with a balance and password
        ATM atm = new ATM(account);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        // Authenticate user
        int maxAttempts = 3;
        boolean authenticated = false;

        for (int i = 0; i < maxAttempts; i++) {
            System.out.print("Enter your password: ");
            String inputPassword = scanner.next();

            if (account.authenticate(inputPassword)) {
                authenticated = true;
                System.out.println("Login successful!\n");
                break;
            } else {
                System.out.println("Incorrect password. Attempt " + (i + 1) + " of " + maxAttempts + ".");
            }
        }

        if (!authenticated) {
            System.out.println("Too many failed attempts. Exiting program.");
            return;
        }

        // Display menu and process user choices
        while (true) {
            atm.displayMenu();
            int choice = scanner.nextInt();
            atm.processUserChoice(choice);
        }
    }
}
