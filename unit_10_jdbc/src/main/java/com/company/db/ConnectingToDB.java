package com.company.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectingToDB {
    public static Connection getConnection() throws Exception {
        try (InputStream is = ConnectingToDB.class.getResourceAsStream("/jdbc.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, user,password);
        } catch (IOException ioException) {
            throw new Exception(ioException);
        }
    }
}
