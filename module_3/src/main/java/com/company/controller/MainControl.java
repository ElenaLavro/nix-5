package com.company.controller;

import com.company.dao.impl.JDBCFinances;
import com.company.dao.impl.JPAOperation;
import com.company.service.OperationService;
import com.company.service.ReportService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MainControl {
    public void run(String user, String password) {
        try (SessionFactory sessionFactory = new Configuration().configure()
                .setProperty("hibernate.connection.username", user)
                .setProperty("hibernate.connection.password", password)
                .buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Properties properties = getProperties(user, password);
            String url = properties.getProperty("url");
            Connection connection = DriverManager.getConnection(url, properties);
            connection.setAutoCommit(false);

            JDBCFinances report = new JDBCFinances(connection);
            JPAOperation operation = new JPAOperation(session);
            ReportService reportService = new ReportService(report);
            OperationService operationService = new OperationService(operation, () -> session);

            Controller controller = new Controller(reportService, operationService);
            controller.run();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }

    private Properties getProperties(String user, String pass) {
        Properties properties = new Properties();
        try (InputStream inputStream = MainControl.class.getResourceAsStream("/ModuleJdbc.properties")) {
            properties.load(inputStream);
            properties.setProperty("user", user);
            properties.setProperty("password", pass);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return properties;
    }
}
