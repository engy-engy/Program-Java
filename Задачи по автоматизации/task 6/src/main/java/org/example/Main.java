package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MainBasket mainBasket = new MainBasket();
        /** Решение */

        mainBasket.addProduct("Холодильник",0);
        mainBasket.addProduct("Чайник",1);
        mainBasket.addProduct("Тостер",2);
        mainBasket.addProduct("Пакет",3);

        mainBasket.getProducts((ArrayList) mainBasket.list);

        mainBasket.removeProduct("Чайник");

        mainBasket.getProducts((ArrayList) mainBasket.list);

        mainBasket.updateProductQuantity("Пылесос",2);

        mainBasket.getProducts((ArrayList) mainBasket.list);

        System.out.println();
        System.out.print("Индекс товара пылесос: ");

        mainBasket.getProductQuantity("Пылесос");

        /**
        Scanner scanner = new Scanner(System.in);
        boolean online = true;
        while (online){
            System.out.println();
            System.out.println();
            System.out.println("  Меню корзины\n"+
                    "  Выберите действие цифрой от 1 до 5");
            System.out.println("  1) Посмотреть список продуктов");
            System.out.println("  2) Добавить продукт");
            System.out.println("  3) Удалить продукт");
            System.out.println("  4) Изменить продукт");
            System.out.println("  5) Очистить корзину");
            System.out.println("  6) Вывести колличество продуктов");
            System.out.println("  7) Выйти");
            int number = scanner.nextInt();

            switch (number){

                case 1:
                    System.out.println();
                    System.out.println("Продкуты");
                    mainBasket.getProducts((ArrayList) mainBasket.list);
                    System.out.println();

                    break;
                case 2:
                    int q = 1;
                    System.out.println("Введите название");
                    String name = mainBasket.scanner.nextLine();
                    while (q == 1) {
                        System.out.println("Введите индекс ");
                        int index = mainBasket.scanner.nextInt();
                        if (index == 0) {
                            mainBasket.list.add(index, name);
                            break;
                        } else {
                            System.out.println("Введите 0");
                        }
                    }
                case 3:
                    System.out.println();
                    System.out.println("Введите название продукта ");
                    String nameProduct = scanner.nextLine();
                    mainBasket.removeProduct(nameProduct);
                    break;
                case 4:
                    System.out.println("Введите продукт");
                    String name1 = mainBasket.scanner.nextLine();
                    System.out.println("Введите индекс ");
                    int key1 = mainBasket.scanner.nextInt();
                    mainBasket.updateProductQuantity(name1,key1);
                    break;
                case 5:
                    mainBasket.clear();
                    break;
                case 6:
                    System.out.println("Введите продукт");
                    String name2 = mainBasket.scanner.nextLine();
                    mainBasket.getProductQuantity(name2);
                    break;
                case 7:
                    online = false;
                    break;
            }
        }
        */
    }

}