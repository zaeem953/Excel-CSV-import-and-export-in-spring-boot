package com.excel.excel.helper;

import com.excel.excel.model.ExcelData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static String[] HEADERS={
            "Id",
            "FirstName",
            "LastName",
            "Gender",
            "Country",
            "Age"
    };

    public static String SHEET_NAME="Java_Export_Excel";

    public static ByteArrayInputStream dataToExcel(List<ExcelData> list) throws IOException {

        Workbook workbook=new XSSFWorkbook();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {

            Sheet sheet=workbook.createSheet(SHEET_NAME);

            //Create Row Headers
            Row row=sheet.createRow(0);

            for (int i=0;i<HEADERS.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            int rowIndex=1;
            for (ExcelData excelData:list){
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(excelData.getId());
                dataRow.createCell(1).setCellValue(excelData.getFirstName());
                dataRow.createCell(2).setCellValue(excelData.getLastName());
                dataRow.createCell(3).setCellValue(excelData.getGender());
                dataRow.createCell(4).setCellValue(excelData.getCountry());
                dataRow.createCell(5).setCellValue(excelData.getAge());
            }
            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in exporting");
        }finally {
            workbook.close();
            out.close();
        }

        return null;
    }
}
