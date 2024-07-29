import java.util.Scanner;

// Step 4: Create a class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient balance
        } else {
            balance -= amount;
            return true;
        }
    }
}

// Step 1: Create a class to represent the ATM machine
public class ATM {
    private BankAccount account;

    // Step 5: Connect the ATM class with the user's bank account class
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Step 3: Implement methods for each option
    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Please collect your cash.");
        } else {
            System.out.println("Insufficient balance. Transaction failed.");
        }
    }

    public void deposit(double amount) {
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    // Step 2: Design the user interface for the ATM
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            double amount;

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    amount = scanner.nextDouble();
                    withdraw(amount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    amount = scanner.nextDouble();
                    deposit(amount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Initial balance for the user's bank account
        BankAccount userAccount = new BankAccount(1000.00);
        // Create an ATM object with the user's bank account
        ATM atm = new ATM(userAccount);
        // Start the ATM interface
        atm.start();
    }
}
