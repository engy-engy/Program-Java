package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] array ;

        ArrayX arrayX = new ArrayX();

        array = arrayX.buildArr(4);
        arrayX.printStream(array);

        array = arrayX.buildArr(8);
        arrayX.print(array);

        array = arrayX.buildArr(12);
        arrayX.print(array);

    }
}