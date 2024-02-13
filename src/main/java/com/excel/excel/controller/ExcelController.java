package com.excel.excel.controller;

import com.excel.excel.helper.CSVExporter;
import com.excel.excel.model.ExcelData;
import com.excel.excel.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/data")
public class ExcelController {

    @Autowired
    private ExcelService excelService;


    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
//        if (ExcelImportHelper.checkExcelFormat(file)){
            System.out.println("huihuihiu");
            this.excelService.importExcel(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved"));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload only Excel file");
    }

    @GetMapping("/allData")
    public List<ExcelData> getAllData(){
        return this.excelService.getAllData();
    }

    @GetMapping("/excel")
    public ResponseEntity<Resource> download() throws IOException {

        String filename="Java_Export_Excel.xlsx";

        ByteArrayInputStream actualData = excelService.exportExcel();
        InputStreamResource file=new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }


    @GetMapping("/csvexport")
    public void exportCSV(HttpServletResponse response) throws IOException {
//        List<ExcelData> data = excelService.getAllData();
        excelService.exportCSV(response);
//        CSVExporter.exportToCSV(response, data);
    }

    @PostMapping("/importCSV")
    public ResponseEntity<?> importCSV(@RequestParam("file") MultipartFile file) throws IOException {
        this.excelService.importCSV(file);
        return ResponseEntity.ok(Map.of("message","CSV File is uploaded and data is saved"));
//        excelService.importExcel(file);
    }

}
