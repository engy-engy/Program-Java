package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class Cube {
    /**Список для элементов диагоналей куба 2х2х2*/
    ArrayList list = new ArrayList();

    /** Получение второго по величине элементов диагоналей куба 2х2х2*/
    public int getMaxElementArray(int[][][] array){
        // Добавление элементов диагоналей куба в список
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                for (int e = 0; e < array.length; e++){
                    list.add(array[i][j][e]);
                }
            }
        }
        // Сортируем список диагоналей
        Collections.sort(list);
        // Удаляем максималый элемент
        list.remove(7);

        String a = Collections.max(list).toString();
        int b = Integer.parseInt(a);
        // Выводим второй по величине элемент
        System.out.println(b);
        return b;
    }

}

