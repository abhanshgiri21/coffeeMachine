/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dunzo.coffeemachinesim.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author abhanshgiri
 */
public class FileService {
    public List<String> readData(String filename) {
        String filePath = getClass().getClassLoader().getResource(filename).getPath();
        List<String> lines = new ArrayList<>();
        Scanner scanner = null;

        try{
            File file = new File(filePath);
            scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found at path : " + filePath);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return lines;
    }
}
