package com.excel.excel.helper;

import com.excel.excel.model.ExcelData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter {

    public static List<ExcelData> importFromCSV(MultipartFile file) throws IOException {
        List<ExcelData> dataList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            // Skip header row
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                ExcelData data = new ExcelData();
                data.setId(Integer.parseInt(line[0]));
                data.setFirstName(line[1]);
                data.setLastName(line[2]);
                data.setGender(line[3]);
                data.setCountry(line[4]);
                data.setAge(Integer.parseInt(line[5]));
                dataList.add(data);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }
}
