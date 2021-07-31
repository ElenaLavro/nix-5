package com;

import com.company.controller.MainControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Module3Main {
    public static void main(String[] args) throws IOException {
        MainControl mainControle = new MainControl();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter name of user");
        String user = bufferedReader.readLine();
        System.out.println("Enter the password");
        String password = bufferedReader.readLine();
        mainControle.run(user, password);
    }
}
