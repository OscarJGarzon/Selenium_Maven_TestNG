package toolBox;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcelFile {

    public void writeExcelFile(String filePath, String sheetName, String[] dataToWrite) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);
        int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
        XSSFRow row = newSheet.getRow(0);
        XSSFRow newRow = newSheet.getRow(rowCount + 1);

        for (int i = 0; i < row.getLastCellNum(); i++) {
            XSSFCell newCell = newRow.createCell(i);
            newCell.setCellValue(dataToWrite[i]);
        }
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        newWorkBook.write(outputStream);
        outputStream.close();
    }

    public void writeCellValue(String filePath, String sheetName, int rowNumber, int cellNumber, String resultText) throws IOException {

        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);
        XSSFRow row = newSheet.getRow(rowNumber);
        XSSFCell firstCell = row.getCell(cellNumber - 1);
        System.out.println("First cell values is: " + firstCell.getStringCellValue());
        XSSFCell nextCell = row.createCell(cellNumber);
        nextCell.setCellValue(resultText);
        System.out.println("Next cell values is: " + nextCell.getStringCellValue());
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(file);
        newWorkBook.write(outputStream);
        outputStream.close();
    }
}
