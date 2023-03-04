package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Cube cube = new Cube();

        Random random = new Random();
        // Рандомное заполнение
        int[][][] array = new int[2][2][2];
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                for (int b = 0; b < array.length; b++){
                    array[i][j][b] = random.nextInt(1,1000);
                }
            }
        }

        cube.getMaxElementArray(array);

    }
}