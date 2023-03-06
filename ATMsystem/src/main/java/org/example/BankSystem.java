package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class BankSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static JSONObject database;
    private static String currentUser;

    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("database.json");
        if (file.exists()) {
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(file);
            database = (JSONObject) parser.parse(fileReader);
            fileReader.close();
        } else {
            database = new JSONObject();
        }
        while (true) {
            System.out.println("Выберите действие: 1) Войти, 2) создать пользователя, 3) завершить работу программы");
            int choice = scanner.nextInt();
            if (choice == 1) {
                login();
                break;
            } else if (choice == 2) {
                createUser();
                break;
            } else if (choice == 3) {
                System.exit(0);
            } else {
                System.out.println("Неправильный ввод, попробуйте снова.");
            }
        }
        while (true) {
            BankMenu.showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewTransactionHistory();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    transferMoney();
                    break;
                case 4:
                    while (true) {
                        System.out.println("Выберите действие: 1) Войти, 2) создать пользователя, 3) завершить работу программы");
                        int option = scanner.nextInt();
                        if (option == 1) {
                            login();
                            break;
                        } else if (option == 2) {
                            createUser();
                            break;
                        } else if (option == 3) {
                            System.exit(0);
                        } else {
                            System.out.println("Неправильный ввод, попробуйте снова.");
                        }
                    }
                    break;
                default:
                    System.out.println("Неправильный ввод, попробуйте снова.");
                    break;
            }
        }
    }

    public static void login() throws IOException {
        System.out.println("Введите имя пользователя:");
        String username = scanner.next();
        System.out.println("Введите пароль:");
        String password = scanner.next();
        JSONObject user = (JSONObject) database.get(username);
        if (user == null) {
            System.out.println("Такого пользователя не существует.");
            createUser();
            return;
        }
        if (!user.get("password").equals(password)) {
            System.out.println("Неправильный пароль.");
            return;
        }
        currentUser = username;
        System.out.println("Вы вошли как " + currentUser + ".");
    }

    public static void createUser() throws IOException {
        System.out.println("Введите имя пользователя:");
        String username = scanner.next();
        JSONObject user = (JSONObject) database.get(username);
        if (user != null) {
            System.out.println("Пользователь с таким именем уже существует.");
            return;
        }
        System.out.println("Введите пароль:");
        String password = scanner.next();
        user = new JSONObject();
        user.put("password", password);
        user.put("balance", 0L);
        database.put(username, user);
        updateDatabase();
        System.out.println("Пользователь " + username + " создан.");
    }

    public static void viewTransactionHistory() throws IOException {
        JSONObject user = (JSONObject) database.get(currentUser);
        JSONArray transactionHistory = (JSONArray) user.get("transactionHistory");
        if (transactionHistory == null) {
            System.out.println("У вас пока нет истории транзакций.");
            return;
        }
        for (Object transactionObject : transactionHistory) {
            JSONObject transaction = (JSONObject) transactionObject;
            System.out.println("Отправитель: " + transaction.get("from") + ", Получатель: " + transaction.get("to") + ", Сумма: " + transaction.get("amount"));
        }
    }

    public static void depositMoney() throws IOException {
        System.out.println("Введите сумму для пополнения:");
        long amount = scanner.nextLong();
        JSONObject user = (JSONObject) database.get(currentUser);
        if (user == null) {
            System.out.println("Ошибка: пользователь не найден.");
            return;
        }
        long balance = (long) user.get("balance");
        balance += amount;
        user.put("balance", balance);
        JSONArray transactionHistory = (JSONArray) user.get("transactionHistory");
        if (transactionHistory == null) {
            transactionHistory = new JSONArray();
            user.put("transactionHistory", transactionHistory);
        }
        JSONObject transaction = new JSONObject();
        transaction.put("from", currentUser);
        transaction.put("to", currentUser);
        transaction.put("amount", amount);
        transactionHistory.add(transaction);
        System.out.println("Баланс обновлен: " + balance + " долларов.");
        updateDatabase();
    }

    public static void transferMoney() throws IOException {
        System.out.println("Введите имя получателя:");
        String recipient = scanner.next();
        JSONObject user = (JSONObject) database.get(currentUser);
        JSONObject recipientUser = (JSONObject) database.get(recipient);
        if (recipientUser == null) {
            System.out.println("Пользователь с таким именем не найден.");
            return;
        }
        System.out.println("Введите сумму для перевода:");
        long amount = scanner.nextLong();
        long balance = (long) user.get("balance");
        if (balance < amount) {
            System.out.println("Недостаточно средств для перевода.");
            return;
        }
        balance -= amount;
        user.put("balance", balance);
        JSONArray transactionHistory = (JSONArray) user.get("transactionHistory");
        if (transactionHistory == null) {
            transactionHistory = new JSONArray();
            user.put("transactionHistory", transactionHistory);
        }
        JSONObject transaction = new JSONObject();
        transaction.put("from", currentUser);
        transaction.put("to", recipient);
        transaction.put("amount", amount);
        transactionHistory.add(transaction);
        long recipientBalance = (long) recipientUser.get("balance");
        recipientBalance += amount;
        recipientUser.put("balance", recipientBalance);
        JSONArray recipientTransactionHistory = (JSONArray) recipientUser.get("transactionHistory");
        if (recipientTransactionHistory == null) {
            recipientTransactionHistory = new JSONArray();
            recipientUser.put("transactionHistory", recipientTransactionHistory);
        }
        JSONObject recipientTransaction = new JSONObject();
        recipientTransaction.put("from", currentUser);
        recipientTransaction.put("to", recipient);
        recipientTransaction.put("amount", amount);
        recipientTransactionHistory.add(recipientTransaction);
        System.out.println("Перевод выполнен успешно.");
        updateDatabase();
    }

    public static void updateDatabase() throws IOException {
        FileWriter fileWriter = new FileWriter("database.json");
        fileWriter.write(database.toJSONString());
        fileWriter.close();
    }
}
