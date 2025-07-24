package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static String getCellValue(String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(new File("src/test/resources/excel/LoginData.xlsx"));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in the Excel file.");
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                throw new RuntimeException("Row " + rowNum + " not found in sheet '" + sheetName + "'.");
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) return "";

            // Handle based on cell type
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue().trim();

                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                    } else {
                        double val = cell.getNumericCellValue();
                        long longVal = (long) val;
                        // Remove decimal if it's an integer
                        if (val == longVal) {
                            return String.valueOf(longVal);
                        } else {
                            return String.valueOf(val);
                        }
                    }

                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());

                case FORMULA:
                    return cell.getCellFormula();

                case BLANK:
                    return "";

                default:
                    return "UNSUPPORTED_CELL_TYPE";
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error reading Excel cell: " + e.getMessage(), e);
        }
    }
}
