package com.excel.excel.helper;

import com.excel.excel.model.ExcelData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelImportHelper {

    //Check File Type
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType=file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats.officedocument.spreadsheetml.sheet")){
            return true;
        }
        else {
            return false;
        }
    }

    public static List<ExcelData> convertExcelDatatoExceltDataEntity(InputStream inputStream){
        List<ExcelData> list=new ArrayList<>();


        try {


            XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
//            System.out.println("Available Sheet Names:");
//            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//                System.out.println(workbook.getSheetName(i));
//            }

            XSSFSheet sheet=workbook.getSheet("Java_Export_Excel");
            int rowNum=0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()){
                Row row = iterator.next();
                if (rowNum == 0){
                    rowNum++;
                    continue;
                }

                Iterator<Cell> cells=row.iterator();

                int eid=0;

                ExcelData excelData=new ExcelData();
                while (cells.hasNext()){
                    Cell cell = cells.next();

                    switch (eid)
                    {
                        case 0:
                            excelData.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            excelData.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            excelData.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            excelData.setGender(cell.getStringCellValue());
                            break;
                        case 4:
                            excelData.setCountry(cell.getStringCellValue());
                            break;
                        case 5:
                            excelData.setAge((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    eid++;
                }
                list.add(excelData);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
