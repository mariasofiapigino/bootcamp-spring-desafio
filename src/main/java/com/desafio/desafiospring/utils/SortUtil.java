package com.desafio.desafiospring.utils;

import java.util.Comparator;

public class SortUtil {
    public static <T> void order(T arr[], Comparator<T> c){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++)
                if (c.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        }
    };
}
