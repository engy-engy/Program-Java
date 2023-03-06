package org.example;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public interface Basket {
    void addProduct(String product, int quantity);
    void removeProduct(String product);
    void updateProductQuantity(String product, int quantity);
    void clear();
    List<String> getProducts();
    int getProductQuantity(String product);
}


class BasketImpl implements Basket {
    private Map<String, Integer> products = new HashMap<>();

    public void addProduct(String product, int quantity) {
        int newQuantity = products.getOrDefault(product, 0) + quantity;
        products.put(product, newQuantity);
    }

    public void removeProduct(String product) {
        products.remove(product);
    }

    public void updateProductQuantity(String product, int quantity) {
        products.put(product, quantity);
    }

    public void clear() {
        products.clear();
    }

    public List<String> getProducts() {
        List<String> productList = new ArrayList<>(products.keySet());
        return productList;
    }

    public int getProductQuantity(String product) {
        return products.getOrDefault(product, 0);
    }
}
