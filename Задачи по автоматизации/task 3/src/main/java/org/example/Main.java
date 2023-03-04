package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[][] array = {
                {2, 2, 4, 5, 1},
                {2, 4, 5, 6, 7},
                {4, 5, 2, 8, 6},
                {5, 8, 3, 2, 2},
                {0, 5, 6, 7, 1}
        };
        /** рандомное заполнение */
        /**
        for (int i = 0; i < array.length; i++){
            for (int e = 0; e < array.length; e++){
            array[i][e] = random.nextInt(1,9);
            System.out.print(array[i][e]);
            }
        } */
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[array.length - 1 - i][i]) {
                min = array[array.length - 1 - i][i];
            }
        }
        System.out.println(min);

    }
}