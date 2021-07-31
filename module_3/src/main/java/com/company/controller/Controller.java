package com.company.controller;

import com.company.model.dto.OperationDTO;
import com.company.service.OperationService;
import com.company.service.ReportService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Controller {
    private final ReportService reportService;
    private final OperationService operationService;
    private BufferedReader bufferedReader;
    private String name;

    public Controller(ReportService reportService, OperationService operationService) {
        this.reportService = reportService;
        this.operationService = operationService;
    }

    public void run() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        do {
            menu();
            choice = bufferedReader.readLine();
            switch (choice) {
                case "0": {
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                case "1": {
                    performOperation();
                }
                case "2": {
                    exportInCSV();

                }
            }
        } while (true);

    }

    private void menu() {
        System.out.println("~~~Welcome to your financial helper~~~");
        System.out.println("Please, select you operation:");
        System.out.println("1 - add new operation");
        System.out.println("2 - export operations in CSV file");
        System.out.println("0 - Exit");
    }

    private void performOperation() {
        try {
            System.out.println("Enter account id: ");
            Long id = Long.parseLong(bufferedReader.readLine());
            Integer sum = Integer.valueOf(bufferedReader.readLine());
            System.out.println("Enter the id category of income");
            Long categoryIncome = Long.parseLong(bufferedReader.readLine());
            System.out.println("Enter the id category of expose");
            Long categoryExpose = Long.parseLong(bufferedReader.readLine());
            LocalDate localDate = LocalDate.now();
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Instant date = localDateTime.toInstant(ZoneOffset.UTC);

            OperationDTO operationDTO = new OperationDTO(id, sum, categoryIncome, categoryExpose, date);
            operationService.createOperation(operationDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportInCSV() {
        try {
            Long id = Long.parseLong(bufferedReader.readLine());
            System.out.println("Input start date in format yyyy-MM-dd");
            String start = bufferedReader.readLine();
            System.out.println("Input end date in format yyyy-MM-dd");
            String end = bufferedReader.readLine();

            LocalDate localDateStart = LocalDate.parse(start);
            LocalDate localDateEnd = LocalDate.parse(end);
            LocalDateTime localDateTimeStart = localDateStart.atStartOfDay();
            LocalDateTime localDateTimeEnd = localDateEnd.atStartOfDay();
            Instant fromDate = localDateTimeStart.toInstant(ZoneOffset.UTC);
            Instant toDate = localDateTimeEnd.toInstant(ZoneOffset.UTC);

            reportService.createReport(id, fromDate, toDate);

        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }
}
