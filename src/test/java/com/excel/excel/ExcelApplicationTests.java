package com.excel.excel;

import com.excel.excel.repos.ExcelRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExcelApplicationTests {

    @Autowired
    private ExcelRepo excelRepo;

    @Test
    void contextLoads() {
        System.out.println("Test");
        excelRepo.findAll().forEach(excelData -> System.out.println(excelData.getFirstName()));
    }

}
