package com.excel.excel.service;

import com.excel.excel.helper.CSVExporter;
import com.excel.excel.helper.CSVImporter;
import com.excel.excel.helper.ExcelHelper;
import com.excel.excel.helper.ExcelImportHelper;
import com.excel.excel.model.ExcelData;
import com.excel.excel.repos.ExcelRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private ExcelRepo excelRepo;

    public ByteArrayInputStream exportExcel() throws IOException {
        List<ExcelData> all = excelRepo.findAll();

        ByteArrayInputStream byteArrayInputStream = ExcelHelper.dataToExcel(all);
        return byteArrayInputStream;
    }

    public void importExcel(MultipartFile file) throws IOException {
        List<ExcelData> excelDataList = ExcelImportHelper.convertExcelDatatoExceltDataEntity(file.getInputStream());
        this.excelRepo.saveAll(excelDataList);
    }

    public List<ExcelData> getAllData(){
        return this.excelRepo.findAll();
    }


    public void exportCSV(HttpServletResponse response) throws IOException {
        List<ExcelData> data = excelRepo.findAll();
        CSVExporter.exportToCSV(response, data);
    }

    public void importCSV(MultipartFile file) throws IOException {
        List<ExcelData> list = CSVImporter.importFromCSV(file);
        this.excelRepo.saveAll(list);
    }
}
