//package com.avdok.lessons.iodemo3;


import java.io.*;
import java.util.Properties;

public class IODemo {

    static public void main(String[] args) {
        String inputFile;
        String outputFile;

        //Read properties file
        Properties prop = new Properties();
        try (BufferedReader br = new BufferedReader(new FileReader("main.properties"))) {

            prop.load(br);

            inputFile = prop.getProperty("if");
            outputFile = prop.getProperty("of");
//            System.out.println(inputFile);
//            System.out.println(outputFile);
        } catch (IOException exc) {
            System.out.println("IO error");
            return;
        }
        int i;
        try (FileInputStream fileIn = new FileInputStream(inputFile);
             FileOutputStream fileOut = new FileOutputStream(outputFile)) {
//            s

            do {
                i = fileIn.read();
                if (i != -1) {
                    fileOut.write(i);
                }
            } while (i != -1);

        } catch (IOException exc) {
            System.out.println("IO error: " + exc.toString());
        }

    }

}
