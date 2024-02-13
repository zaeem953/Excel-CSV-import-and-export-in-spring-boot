package com.excel.excel.helper;

import com.excel.excel.model.ExcelData;
import com.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public static void exportToCSV(HttpServletResponse response, List<ExcelData> data) throws IOException {
        String filename = "DB_CSV-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            // Write header row
            csvWriter.writeNext(new String[]{"Id", "First_Name", "Last_Name", "Gender", "Country", "Age"});

            // Write data rows
            for (ExcelData rowData : data) {
                csvWriter.writeNext(new String[]{
                        String.valueOf(rowData.getId()),
                        rowData.getFirstName(),
                        rowData.getLastName(),
                        rowData.getGender(),
                        rowData.getCountry(),
                        String.valueOf(rowData.getAge())
                });
            }
        }
    }
}

