package org.example;

import java.util.*;

public class MainBasket implements Basket{
    Map<Integer, String> states = new HashMap<Integer, String>();
    List<String> list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    /** Добавить продукт*/
    public void put(Integer key, String value) {
        states.put(key,value);
    }
    /** Получить значение по ключу*/
    public void get(Integer key){
        String first = states.get(key);
        System.out.println(first);
    }
    /** Получить весь набор ключей*/
    public void getAllKey(Map<Integer, String> states){
        Set<Integer> keys = states.keySet();
        System.out.println(keys);
    }
    /** получить набор всех значений */
    public void getAllValue(Map<Integer, String> states) {
        Collection<String> values = this.states.values();
        System.out.println(values);
    }
    /** заменить значение по ключу*/
    public void replaceElement(Integer key, String value){

        if (states.containsKey(key)){
            states.replace(key, value);
            System.out.println("Успешная замена");
        }
        else {System.out.println("Ключ не существует,попробуйте еще раз.");}
    }
    /** Удаалить значение по ключу*/
    public void removeKey(Integer key){
        if (states.containsKey(key)){
            states.remove(key);
            System.out.println("Успешно удалено");
        }
        else {System.out.println("Ключ не существует,попробуйте еще раз.");}
    }

    @Override
    public List<String> getProducts(ArrayList list) {
        for (int i = 0; i < list.toArray().length; i++){
            String el = (String) list.get(i);
            System.out.print(el+"\t");
        }
        return list;
    }

    @Override
    public void addProduct(String value, Integer key) {

        list.add(key,value);

    }

    @Override
    public void removeProduct(String product) {
        System.out.println();
        list.remove(product);
        System.out.println("Удалено");

    }
    /** Изменить продкут*/
    @Override
    public void updateProductQuantity(String value, Integer key) {
        System.out.println();
        String rem = list.get(key);
        list.remove(rem);
        list.add(key,value);
        System.out.println("обновлено");

    }
    /** Очистить*/
    @Override
    public void clear() {
        list.clear();
    }
    /** Получить количество продуктов*/
    @Override
    public int getProductQuantity(String product) {
        int index = list.indexOf(product);
       System.out.println(index);
       return index;
    }
}
