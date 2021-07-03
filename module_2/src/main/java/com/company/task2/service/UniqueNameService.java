package com.company.task2.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniqueNameService {
    public String convertDateToList() {
        List<String> myListOfNames = new ArrayList<>();
        URL resource = UniqueNameService.class.getResource("/task2/Names.csv");
        ArrayList<String> uniqueNames = new ArrayList<>();
        try {
            FileReader fr = new FileReader(Paths.get(resource.toURI()).toFile());
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String s = sc.nextLine();
                myListOfNames.add(s);
            }
            for (String name : myListOfNames) {
                if (uniqueNames.contains(name)) {
                    uniqueNames.remove(name);
                } else {
                    uniqueNames.add(name);
                }
            }

        } catch (FileNotFoundException | URISyntaxException e) {
            System.out.println("Please check the correctness of the input data and repeat the operation again");
        }
        return uniqueNames.get(0);
    }
}
