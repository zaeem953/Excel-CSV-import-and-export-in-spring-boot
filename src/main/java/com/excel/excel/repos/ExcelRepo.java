package com.excel.excel.repos;

import com.excel.excel.model.ExcelData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcelRepo extends JpaRepository<ExcelData,Integer> {
}
