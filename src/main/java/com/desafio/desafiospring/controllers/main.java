package com.desafio.desafiospring.controllers;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class main {
    public static final String SEPARATOR=";";
    public static final String QUOTE="\"";

    public static void main(String[] args) throws IOException {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:adbProductos.json");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String[] removeTrailingQuotes(String[] fields) {

        String result[] = new String[fields.length];

        for (int i=0;i<result.length;i++){
            result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
        }
        return result;
    }
}
