public class Account {
    private double balance;
    private final double dailyWithdrawalLimit = 5000.00;
    private double dailyWithdrawnAmount = 0.00;
    private final String password; // Add password field

    public Account(double initialBalance, String password) {
        this.balance = initialBalance;
        this.password = password; // Initialize password
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

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword); // Validate password
    }
}
