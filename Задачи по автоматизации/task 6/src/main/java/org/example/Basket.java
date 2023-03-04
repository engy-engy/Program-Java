package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Basket {

    /** Список продуктов*/
    List<String> getProducts(ArrayList list);

    /** Добавить продукт*/
    void addProduct(String product, Integer quantity);
    /**  Удалить продукт*/
    void removeProduct(String  product);
    /** Изменить продукт */
    void updateProductQuantity(String product, Integer quantity);
    /** Очистить список продуктов*/
    void clear();
    /** Колличество продуктов */
    int getProductQuantity(String product);
}

