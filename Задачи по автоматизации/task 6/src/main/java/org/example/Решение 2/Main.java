package org.example;

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



        Basket basket = new BasketImpl();
        basket.addProduct("Хлеб", 2);
        basket.addProduct("Молоко", 1);
        System.out.println("Продукты в корзине: " + basket.getProducts());
        System.out.println("Количество хлеба в корзине: " + basket.getProductQuantity("Хлеб"));
        basket.updateProductQuantity("Хлеб", 3);
        System.out.println("Количество хлеба после обновления: " + basket.getProductQuantity("Хлеб"));
        basket.removeProduct("Молоко");
        System.out.println("Продукты в корзине после удаления молока: " + basket.getProducts());
        System.out.println("Количество молока в корзине: " + basket.getProductQuantity("Молоко"));
        System.out.println("Продукты в корзине: "+ basket.getProducts());
        basket.clear();
        System.out.println("Продукты в корзине после очистки: "+ basket.getProducts());



    }
}