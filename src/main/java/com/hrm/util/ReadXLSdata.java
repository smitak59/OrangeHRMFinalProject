package com.hrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadXLSdata {
	
	
	@DataProvider(name = "bvtdata")
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {
		
		String excelsheetName = m.getName();//method m is reflecting the sheetname and method name in IndexPage=>LoginTest
		//creating instance of file
		File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testdata.xlsx");
        FileInputStream fis = new FileInputStream(f);
        
        XSSFWorkbook wb = new XSSFWorkbook(fis); //open workbook where is sheet available
		XSSFSheet sheetName = wb.getSheet(excelsheetName);//read data from sheet
		
        
        //dynamically count the rows and columns
        int totalRows = sheetName.getLastRowNum();
        System.out.println(totalRows);
        
        //capture the logical row
        Row rowCells = sheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
        System.out.println(totalCols);
        
        DataFormatter format = new DataFormatter();
        
        String testData[][] = new String[totalRows][totalCols];
        
        //start reading data
        for(int i= 1; i<=totalRows; i++) {
        	for(int j=0; j<totalCols; j++) {
        		testData[i-1][j]= format.formatCellValue(sheetName.getRow(i).getCell(j));
        	    System.out.println(testData[i-1][j]);
        	}
        }
        return testData;
	}

}
