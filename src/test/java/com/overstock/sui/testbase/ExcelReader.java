package com.overstock.sui.testbase;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class ExcelReader {
    //
    String fileName;
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    //
    public ExcelReader(String fileName) throws IOException {
        this.fileName = fileName;
        try {
            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            int col_Num = 0;
            int index = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().equals(colName)) {
                    col_Num = i;
                }
            }
            row = sheet.getRow(rowNum - 1);
            XSSFCell cell = row.getCell(col_Num);
            if (cell.getCellTypeEnum() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellTypeEnum() == CellType.BLANK) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    public String getCellData(String sheetName, int colName, int rowNum) {
        try {
            int index = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            row = sheet.getRow(rowNum - 1);
            XSSFCell cell = row.getCell(colName);
            if (cell.getCellTypeEnum() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellTypeEnum() == CellType.BLANK) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //
    public int getRowCount(String sheetName) {
        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) {
                return 0;
            } else {
                sheet = workbook.getSheetAt(index);
                int number = sheet.getLastRowNum() + 1;
                return number;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //
    public int getColumnCount(String sheetName) {
        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) {
                return 0;
            } else {
                sheet = workbook.getSheet(sheetName);
                row = sheet.getRow(0);
                return row.getLastCellNum();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //
    public String randomUrl() {
        String url;
        Random randomNum = new Random();
        int rowNum = 1;
        for (int i=1; i<=1; i++) {      // we need only 1 random number hence i<=1
            rowNum = randomNum.nextInt(getRowCount("Sheet1"));      // Random number from the total number of Url's in the excel file
            break;
        }
        url = getCellData("Sheet1", 0, rowNum );
        return url;
    }

}
