package toolBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

    public void readExcel(String fileName, String sheetName) throws IOException {
        Path path = Paths.get("");
        String filePath= path.toAbsolutePath().toString() + "\\src\\test\\resources\\DataEntry\\"+fileName+".xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);
        int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();

        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = newSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                System.out.println(row.getCell(j).getStringCellValue() + "||");
            }
        }
    }


    public String getCellValue(String fileName, String sheetName, int rowNumber, int cellNumber) throws IOException {
        Path path = Paths.get("");
        String filePath= path.toAbsolutePath().toString() + "\\src\\test\\resources\\DataEntry\\"+fileName+".xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);
        XSSFRow row = newSheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(cellNumber);

        return cell.getStringCellValue();
    }
    public int getRowCount(String fileName,String sheetName) throws IOException {
        Path path = Paths.get("");
        String filePath= path.toAbsolutePath().toString() + "\\src\\test\\resources\\DataEntry\\"+fileName+".xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);

        //int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
        int rowCount = newSheet.getLastRowNum();
        return rowCount;
    }

    public int getColCount(String fileName,String sheetName) throws IOException {
        Path path = Paths.get("");
        String filePath= path.toAbsolutePath().toString() + "\\src\\test\\resources\\DataEntry\\"+fileName+".xlsx";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook newWorkBook = new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWorkBook.getSheet(sheetName);
        int colCount = newSheet.getColumnOutlineLevel(getRowCount(fileName,sheetName));
        return colCount;
    }
}
