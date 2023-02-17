package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainCalculator calc = new MainCalculator();
        System.out.println("Enter the first number: ");
        int number1 = scanner.nextInt();
        System.out.println("Enter the second number: ");
        int number2 = scanner.nextInt();

        System.out.println("Enter operation:  + - * / ");
        String operation = scanner.next();

        switch (operation){
            case "+":
                System.out.println(calc.Sum(number1,number2));
                break;
            case "-":
                System.out.println(calc.Minus(number1,number2));
                break;
            case "*":
                System.out.println(calc.Multi(number1,number2));
                break;
            case "/":
                System.out.println(calc.Del(number1,number2));
                break;
            default:
                System.out.println("Incorrect operation");

        }


    }
}