
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("\n================================");
        System.out.println("       WELCOME TO OIBSIP ATM");
        System.out.println("================================");

        Account account = login();

        if (account == null) {
            System.out.println("Account locked. Access denied.");
            return;
        }

        System.out.println("\nLogin successful!");

        int choice;

        do {
            displayMenu();

            System.out.print("Enter your choice: ");
            choice = readInt();

            switch (choice) {

                case 1:
                    checkBalance(account);
                    break;

                case 2:
                    deposit(account);
                    break;

                case 3:
                    withdraw(account);
                    break;

                case 4:
                    transfer(account);
                    break;

                case 5:
                    showTransactions(account);
                    break;

                case 6:
                    System.out.println("\nThank you for using OIBSIP ATM!");
                    System.out.println("Have a nice day!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);
    }

    private Account login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.authenticate(userId, pin);

            if (account != null) {
                return account;
            }

            attempts++;

            System.out.println("Invalid User ID or PIN.");
            System.out.println("Attempts remaining: " + (3 - attempts));
        }

        return null;
    }

    private void displayMenu() {

        System.out.println("\n========== ATM MENU ==========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.println("==============================");
    }

    private void checkBalance(Account account) {

        System.out.printf(
            "\nAvailable Balance: Rs. %.2f%n",
            account.getBalance()
        );
    }

    private void deposit(Account account) {

        System.out.print("Enter deposit amount: Rs. ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }

        account.deposit(amount);

        account.addTransaction(
            new Transaction("DEPOSIT", amount, "Money deposited")
        );

        System.out.println("Deposit successful!");
        checkBalance(account);
    }

    private void withdraw(Account account) {

        System.out.print("Enter withdrawal amount: Rs. ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }

        if (account.withdraw(amount)) {

            account.addTransaction(
                new Transaction("WITHDRAWAL", amount, "Money withdrawn")
            );

            System.out.println("Please collect your cash.");
            checkBalance(account);

        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private void transfer(Account sender) {

        System.out.print("Enter receiver User ID: ");
        String receiverId = scanner.nextLine();

        Account receiver = bank.getAccount(receiverId);

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return;
        }

        if (receiver == sender) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }

        System.out.print("Enter transfer amount: Rs. ");
        double amount = readDouble();

        if (amount <= 0) {
            System.out.println("Enter a valid amount.");
            return;
        }

        if (sender.withdraw(amount)) {

            receiver.deposit(amount);

            sender.addTransaction(
                new Transaction(
                    "TRANSFER OUT",
                    amount,
                    "Transferred to " + receiverId
                )
            );

            receiver.addTransaction(
                new Transaction(
                    "TRANSFER IN",
                    amount,
                    "Received from " + sender.getUserId()
                )
            );

            System.out.println("Transfer successful!");
            checkBalance(sender);

        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private void showTransactions(Account account) {

        System.out.println("\n======= TRANSACTION HISTORY =======");

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid amount: ");
            }
        }
    }
}
