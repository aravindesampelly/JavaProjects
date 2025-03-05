package ATM;

import java.io.*;
import java.util.Scanner;

public class ATM {
    private static final String BALANCE_FILE = "balance.txt";
    private static final int PIN = 1234;
    private int balance;

    public ATM() {
        balance = readBalance();
    }

    public void userAuthentication() {
        System.out.println("Please Enter your PIN");
        Scanner sc = new Scanner(System.in);
        int custPin = sc.nextInt();
        if (custPin == PIN) {
            optionMenu();
        } else {
            System.out.println("Incorrect PIN! Please try again.");
        }
    }

    public void optionMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose An Operation:");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> depositMoney();
                case 2 -> withdrawMoney();
                case 3 -> checkBalance();
                case 4 -> {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    saveBalance();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public void depositMoney() {
        System.out.println("Enter amount to deposit:");
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        if (money > 0) {
            balance += money;
            System.out.println("Successfully deposited Rs." + money);
            saveBalance();
        } else {
            System.out.println("Invalid amount! Try again.");
        }
    }

    public void withdrawMoney() {
        System.out.println("Enter amount to withdraw:");
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        if (money > 0 && money <= balance) {
            balance -= money;
            System.out.println("Successfully withdrawn Rs." + money);
            saveBalance();
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void checkBalance() {
        System.out.println("Available Balance: Rs." + balance);
    }

    private int readBalance() {
        try (BufferedReader br = new BufferedReader(new FileReader(BALANCE_FILE))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            return 100000; // Default balance if file doesn't exist or has errors
        }
    }

    private void saveBalance() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BALANCE_FILE))) {
            bw.write(String.valueOf(balance));
        } catch (IOException e) {
            System.out.println("Error saving balance!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to ATM");
        ATM obj = new ATM();
        obj.userAuthentication();
    }
}
