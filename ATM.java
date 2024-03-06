import java.util.Scanner;

public class ATM {
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        authenticateUser();
        if (currentUser != null) {
            displayMenu();
        }
    }

    private void authenticateUser() {
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        // In a real system, you would check against a database of users
        // For simplicity, let's hardcode one user here
        if (userID.equals("123") && userPIN.equals("456")) {
            currentUser = new User(userID, userPIN, 1000.0); // Sample initial balance
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void displayMenu() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 0:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + currentUser.getAccountBalance());
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else if (amount > currentUser.getAccountBalance()) {
            System.out.println("Insufficient funds.");
        } else {
            double newBalance = currentUser.getAccountBalance() - amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("$" + amount + " withdrawn successfully.");
            System.out.println("New Balance: $" + newBalance);
        }
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            double newBalance = currentUser.getAccountBalance() + amount;
            currentUser.setAccountBalance(newBalance);
            System.out.println("$" + amount + " deposited successfully.");
            System.out.println("New Balance: $" + newBalance);
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
