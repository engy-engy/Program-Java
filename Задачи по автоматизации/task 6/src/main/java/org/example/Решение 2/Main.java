package org.example;

public class Main {
    public static void main(String[] args) {

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
