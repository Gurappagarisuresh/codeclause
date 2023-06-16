import java.util.HashMap;
import java.util.Scanner;

class ATM {
    private HashMap<String, String> users;
    private HashMap<String, Double> accountBalances;
    private String currentUser;

    public ATM() {
        users = new HashMap<>();
        accountBalances = new HashMap<>();
        // Initialize some sample users and their account balances
        users.put("user1", "1234");
        users.put("user2", "5678");
        accountBalances.put("user1", 1000.0);
        accountBalances.put("user2", 2000.0);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Prompt for user id and pin
        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        System.out.print("Enter user pin: ");
        String userPin = scanner.nextLine();

        if (authenticateUser(userId, userPin)) {
            currentUser = userId;
            System.out.println("Login successful. Welcome, " + currentUser + "!");
            showMenu(scanner);
        } else {
            System.out.println("Invalid user id or pin. Exiting...");
        }
    }

    private boolean authenticateUser(String userId, String userPin) {
        if (users.containsKey(userId) && users.get(userId).equals(userPin)) {
            return true;
        }
        return false;
    }

    private void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    showTransactionsHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user id: ");
                    String recipientId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    transfer(recipientId, transferAmount);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showTransactionsHistory() {
        System.out.println("\nTransaction History for " + currentUser);
        // Implement code to retrieve and display transaction history for the current user
    }

    private void withdraw(double amount) {
        if (amount > 0 && accountBalances.get(currentUser) >= amount) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) - amount);
            System.out.println("Withdrawal successful. Remaining balance: " + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    private void deposit(double amount) {
        if (amount > 0) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) + amount);
            System.out.println("Deposit successful. Updated balance: " + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer(String recipientId, double amount) {
        if (users.containsKey(recipientId) && amount > 0 && accountBalances.get(currentUser) >= amount) {
            accountBalances.put(currentUser, accountBalances.get(currentUser) - amount);
            accountBalances.put(recipientId, accountBalances.get(recipientId) + amount);
            System.out.println("Transfer successful. Remaining balance: " + accountBalances.get(currentUser));
        } else {
            System.out.println("Invalid recipient id, amount, or insufficient balance.");
        }
    }
}

public class atm1
 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
