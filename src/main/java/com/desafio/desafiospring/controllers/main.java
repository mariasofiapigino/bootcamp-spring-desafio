package com.desafio.desafiospring.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class main {
    public static final String SEPARATOR=";";
    public static final String QUOTE="\"";

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;

        try {

            br =new BufferedReader(new FileReader("resources/dbProductos.csv"));
            String line = br.readLine();
            while (null!=line) {
                String [] fields = line.split(SEPARATOR);
                System.out.println(Arrays.toString(fields));

                fields = removeTrailingQuotes(fields);
                System.out.println(Arrays.toString(fields));

                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null!=br) {
                br.close();
            }
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
