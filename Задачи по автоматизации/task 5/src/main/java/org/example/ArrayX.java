package org.example;

import java.util.Arrays;

public  class ArrayX {
    // формируем двумерный массив
    public static int[][] buildArr(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0, v = n / 2; v-- > 1; i++) {
            for (int j = i; j < n - i; j++) {
                arr[i][j] = v;       // верхняя строка
                arr[j][i] = v;       // левая колонка
                arr[n-1-i][j] = v;   // нижняя строка
                arr[j][n-1-i] = v; // правая колонка
            }
        }
        return arr;
    }
    // вывод массива
    public static void print(int[][] arr) {
        System.out.println("arr " + arr.length + " x " + arr[0].length);
        for (int[] r : arr) {
            System.out.println(Arrays.toString(r).replaceAll("\\D+", ""));
        }
        System.out.println("----");
    }
    // вывод массива через стрим
    public static void printStream(int[][] arr) {
        System.out.println("arr " + arr.length + " x " + arr[0].length);
        Arrays.stream(arr)
                .map(Arrays::toString)
                .map(r -> r.replaceAll("\\D+", ""))
                .forEach(System.out::println);
        System.out.println("----");
    }
}
