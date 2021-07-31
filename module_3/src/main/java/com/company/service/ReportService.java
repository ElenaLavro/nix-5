package com.company.service;

import com.company.dao.impl.JDBCFinances;
import com.company.model.dto.ExportDTO;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final JDBCFinances report;

    public ReportService(JDBCFinances report) {
        this.report = report;
    }

    public void createReport(Long id, Instant startDate, Instant endDate) throws SQLException, IOException {
        logger.debug("Start creating Report");
        List<ExportDTO> list = report.getOperationByPeriod(id, startDate, endDate);
        logger.debug("List of operation uploaded");
        Long income = calculateIncome(list);
        Long balance = calculateBalance(income, list);
        logger.debug("Start generating CSV file");
        generateFile(filling(list), income, balance);
    }

    public Long calculateIncome(List<ExportDTO> exportDTOList) {
        logger.debug("Start calculating income value");
        Long income = 0L;
        for (ExportDTO e : exportDTOList) {
            if (e.getSum() > 0) {
                income += e.getSum();
            }
        }
        logger.debug("Calculating income value is finished");
        return income;
    }

    public Long calculateBalance(Long income, List<ExportDTO> exportDTOList) {
        logger.debug("Start calculating balance");
        Long expense = 0L;
        logger.debug("Start calculating expense value");
        for (ExportDTO e : exportDTOList) {
            if (e.getSum() < 0) {
                expense += e.getSum();
            }
        }
        logger.debug("Calculating expense value finished");
        logger.debug("Calculating balance finished");
        return Math.subtractExact(income, expense);
    }

    public void generateFile(List<String[]> operation, Long income, Long balance) throws IOException {
        logger.debug("Start creating CSV file");
        File file = new File("reports/report.csv");
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(operation);
            writer.writeNext(new String[]{"Income: ", String.valueOf(income)});
            writer.writeNext(new String[]{"Balance: ", String.valueOf(balance)});
        } catch (IOException exception) {
            logger.error("Failed to generate CSV");
            throw new IOException("Failed to generate CSV");
        }
    }

    public List<String[]> filling(List<ExportDTO> exportDTOList) {
        logger.debug("Start filling CSV file by data");
        List<String[]> filled = new ArrayList<>();
        String[] header = {"Account Id", "Type of account", "Balance", "Sum of operation", "Date", "Username"};
        filled.add(header);
        logger.debug("Creating header finished successfully");
        logger.debug("Start filling other data");
        for (ExportDTO exportDTO : exportDTOList) {
            String[] o = new String[6];
            o[0] = String.valueOf(exportDTO.getAccountID());
            o[1] = exportDTO.getTypeOfAccount();
            o[2] = String.valueOf(exportDTO.getBalance());
            o[3] = String.valueOf(exportDTO.getSum());
            o[4] = String.valueOf(exportDTO.getDate());
            o[5] = exportDTO.getUserName();
            filled.add(o);
        }
        logger.debug("Filling is finished successfully");
        return filled;
    }
}
