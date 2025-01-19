import java.util.Scanner;

public class AtmManagement {

    public static class Account {
        private double balance;
        private final double dailyWithdrawalLimit = 10000.00;
        private double dailyWithdrawnAmount = 0.00;

        public Account(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public boolean deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be greater than zero.");
                return false;
            }
            balance += amount;
            System.out.println("Deposit successful. New Balance: " + balance);
            return true;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Withdrawal amount must be greater than zero.");
                return false;
            }
            if (amount > balance) {
                System.out.println("Insufficient balance.");
                return false;
            }
            if (dailyWithdrawnAmount + amount > dailyWithdrawalLimit) {
                System.out.println("Daily withdrawal limit exceeded.");
                return false;
            }
            balance -= amount;
            dailyWithdrawnAmount += amount;
            System.out.println("Withdrawal successful. New Balance: " + balance);
            return true;
        }

        public void resetDailyLimit() {
            dailyWithdrawnAmount = 0.00;
        }
    }

    public static class ATM {
        private Account account;
        private String password = "1234";

        public ATM(Account account) {
            this.account = account;
        }

        public boolean authenticateUser(String inputPassword) {
            if (this.password.equals(inputPassword)) {
                return true;
            } else {
                System.out.println("Authentication failed. Incorrect password.");
                return false;
            }
        }

        public void displayMenu() {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
        }

        public void processUserChoice(int choice) {
            Scanner scanner = new Scanner(System.in);
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Account account = new Account(50000.00);
        ATM atm = new ATM(account);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        int attempts = 0;
        boolean authenticated = false;
        while (!authenticated && attempts < 3) {
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            authenticated = atm.authenticateUser(password);
            if (!authenticated) {
                attempts++;
                if (attempts == 3) {
                    System.out.println("Too many failed attempts. Exiting program.");
                    System.exit(0);
                } else {
                    System.out.println("You have " + (3 - attempts) + " attempt(s) remaining.");
                }
            }
        }

        while (true) {
            atm.displayMenu();
            int choice = scanner.nextInt();
            atm.processUserChoice(choice);
        }
    }
}
