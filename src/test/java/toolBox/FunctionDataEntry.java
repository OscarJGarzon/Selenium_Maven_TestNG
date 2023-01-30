package toolBox;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FunctionDataEntry {
    String filePath;
    Sheet sh;

    public FunctionDataEntry(String fileName, String sheetName) {
        System.out.println("Load Excel Sheet.........");
        Path path = Paths.get("");
        filePath = path.toAbsolutePath().toString() + "\\src\\test\\resources\\DataEntry\\" + fileName;
       // Open File
        File testDataFile = new File(filePath);
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(testDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sh = wb.getSheet(sheetName);
    }

    public HashMap<String, HashMap<String,String>> getTestDataInMap(int rowNum) {
        //Read data row by row and put in map
        HashMap<String, HashMap<String,String>> MasterData = new HashMap<String, HashMap<String,String>>();
        HashMap<String,String> testCaseData = new HashMap<String,String> ();

            for(int j = 0; j < getColCount(); j++){
                sh.getRow(j).getCell(j).getStringCellValue();
                testCaseData.put(sh.getRow(0).getCell(j).toString(), sh.getRow(rowNum).getCell(j).toString());
            }
            MasterData.put(sh.getRow(0).getCell(0).toString(), testCaseData);
       // }
        return MasterData;
    }

    public int getRowCount() {
        return sh.getLastRowNum();
    }

    public int getColCount() {
        return sh.getRow(0).getLastCellNum();
    }
}
