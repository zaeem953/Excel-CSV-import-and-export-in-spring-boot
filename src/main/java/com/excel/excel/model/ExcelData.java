package com.excel.excel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exceldata")
@Data
public class ExcelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "First_Name")
    private String FirstName;
    @Column(name = "Last_Name")
    private String LastName;
    @Column(name = "Gender")
    private String Gender;
    @Column(name = "Country")
    private String Country;
    @Column(name = "Age")
    private int Age;
}
