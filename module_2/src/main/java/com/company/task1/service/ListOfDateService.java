package com.company.task1.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ListOfDateService {

    public List<String> readingInputFile() {
        List<String> myDateList = new ArrayList<>();
        URL resource = ListOfDateService.class.getResource("/task1/InputDate.txt");
        try (FileReader fr = new FileReader(Paths.get(resource.toURI()).toFile());
             Scanner sc = new Scanner(fr)) {
            while (sc.hasNext()) {
                String s = sc.nextLine();
                s = s.replaceAll("\"", "");
                if (dateValidation(s, "dd/MM/yyy")
                        || dateValidation(s, "yyyy/MM/dd")
                        || dateValidation(s, "dd-MM-yyyy")
                        || dateValidation(s, "MM-dd-yyyy")) {
                    myDateList.add(s);
                }
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Something goes wrong. Please, check date in the input file and repeat action");
        }
        return myDateList;
    }


    public void writingOutputFile(List<String> inputDate) {
        File file = new File("D:\\Java\\nix5-offline\\module_2\\src\\main\\resources\\task1\\OutputDate.txt");
        try (
                FileWriter fw = new FileWriter(String.valueOf(file));
        ) {
            for (String s : inputDate) {
                s = s.replaceAll("-", "").replaceAll("/", "");
                fw.write(s);
                fw.write("\n");
            }
        } catch (IOException ioException) {
            System.out.println("Problem with writing file. Please, check input date and the output file");
        }
    }

    private static boolean dateValidation(final String date, String format) {
        boolean valid = false;
        final DateFormat sdf = new SimpleDateFormat(format, new Locale("uk", "UA"));
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            valid = true;
        } catch (ParseException ignored) {
        }
        return valid;
    }

}
