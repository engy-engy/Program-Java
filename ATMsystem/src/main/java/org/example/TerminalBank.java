package org.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TerminalBank {

    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        loadUsers();
        showWelcomeMessage();
        int option;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid option!");
            return;
        }
        while (option != 3) {
            switch (option) {
                case 1:
                    logIn();
                    break;
                case 2:
                    createUser();
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
            showWelcomeMessage();
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid option!");
                option = 0;
            }
        }
        System.out.println("Thank you for using our Banking System!");
        scanner.close();
    }

    private static void createUser() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        if (userExists(username)) {
            System.out.println("User already exists!");
            return;
        }
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        Random random = new Random();
        int accountNumber = 1000 + random.nextInt(8999);
        User newUser = new User(username, password, accountNumber);
        users.add(newUser);
        currentUser = newUser;
        saveUser(newUser);
        showUserMenu();
        int option;
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid option!");
            return;
        }
        while (option != 7) {
            switch (option) {
                case 1:
                    depositMoney();
                    break;
                case 2:
                    viewBalance();
                    break;
                case 3:
                    transferMoney();
                    break;
                case 4:
                    viewTransactionHistory();
                    break;
                case 5:
                    viewAccountNumber();
                    break;
                case 6:
                    withdrawMoney();
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
            showUserMenu();
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid option!");
                option = 0;
            }
        }
        currentUser = null;
    }

    private static void logIn() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                showUserMenu();
                int option;
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid option!");
                    return;
                }
                while (option != 7) {
                    switch (option) {
                        case 1:
                            depositMoney();
                            break;
                        case 2:
                            viewBalance();
                            break;
                        case 3:
                            transferMoney();
                            break;
                        case 4:
                            viewTransactionHistory();
                            break;
                        case 5:
                            viewAccountNumber();
                            break;
                        case 6:
                            withdrawMoney();
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                    showUserMenu();
                    try {
                        option = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid option!");
                        option = 0;
                    }
                }
                currentUser = null;
                return;
            }
        }
        System.out.println("Invalid username or password!");
    }

    private static void depositMoney() {
        System.out.println("Enter the amount to deposit:");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid amount!");
            return;
        }
        currentUser.deposit(amount);
        saveUser(currentUser);
    }

    private static void viewBalance() {
        System.out.println("Your balance is " + currentUser.getBalance());
    }

    private static void transferMoney() {
        System.out.println("Enter the recipient's account number:");
        int recipientAccountNumber;
        try {
            recipientAccountNumber = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid account number!");
            return;
        }
        System.out.println("Enter the recipient's username:");
        String recipientUsername = scanner.nextLine();
        User recipient = null;
        for (User user : users) {
            if (user.getUsername().equals(recipientUsername) && user.getAccountNumber() == recipientAccountNumber) {
                recipient = user;
                break;
            }
        }
        if (recipient == null) {
            System.out.println("Recipient not found!");
            return;
        }
        System.out.println("Enter the amount to transfer:");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid amount!");
            return;
        }
        currentUser.transfer(amount, recipient);
        saveUser(currentUser);
        saveUser(recipient);
    }

    private static void viewTransactionHistory() {
        currentUser.viewTransactionHistory();
    }

    private static void viewAccountNumber() {
        System.out.println("Your account number is " + currentUser.getAccountNumber());
    }

    private static void withdrawMoney() {
        System.out.println("Enter the amount to withdraw:");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid amount!");
            return;
        }
        currentUser.withdraw(amount);
        saveUser(currentUser);
    }

    private static void showWelcomeMessage() {
        System.out.println("Welcome to our Banking System!");
        System.out.println("Choose an option:");
        System.out.println("1. Log in");
        System.out.println("2. Create an account");
        System.out.println("3. Exit the program");
    }

    private static void showUserMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Deposit money");
        System.out.println("2. View balance");
        System.out.println("3. Transfer money");
        System.out.println("4. View transaction history");
        System.out.println("5. View account number");
        System.out.println("6. Withdraw money");
        System.out.println("7. Change user");
    }

    private static boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void loadUsers() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("users.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");
                int accountNumber = ((Long) jsonObject.get("accountNumber")).intValue();
                double balance = (Double) jsonObject.get("balance");
                ArrayList<String> transactionHistory = (ArrayList<String>) jsonObject.get("transactionHistory");
                User user = new User(username, password, accountNumber, balance, transactionHistory);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private static void saveUser(User user) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", user.getUsername());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("accountNumber", user.getAccountNumber());
            jsonObject.put("balance", user.getBalance());
            jsonObject.put("transactionHistory", user.getTransactionHistory());
            FileWriter fileWriter = new FileWriter("users.json", true);
            fileWriter.write(jsonObject.toJSONString() + "\\\\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
}

class User {

    private String username;
    private String password;
    private int accountNumber;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String username, String password, int accountNumber) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public User(String username, String password, int accountNumber, double balance, ArrayList<String> transactionHistory) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit of $" + amount);
        System.out.println("$" + amount + " deposited successfully!");
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrawal of $" + amount);
        System.out.println("$" + amount + " withdrawn successfully!");
    }

    public void transfer(double amount, User recipient) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return;
        }
        balance -= amount;
        recipient.deposit(amount);
        transactionHistory.add("Transfer of $" + amount + " to " + recipient.getUsername());
        System.out.println("$" + amount + " transferred to " + recipient.getUsername() + " successfully!");
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction history:");
        for (String transaction : transactionHistory) {
            System.out.println("- " + transaction);
        }
    }
}

