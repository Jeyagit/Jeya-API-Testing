import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public String readFromFileAndConvertToString(String filePath) throws IOException {
        String line, filecontent = "";
        FileReader fileReader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fileReader);
        while ((line = br.readLine()) !=null) {
            filecontent +=line;
        }
        return filecontent;
    }

    public Object[] readFromExcelAndReturnDataAsList(String fileName, String sheetName) throws IOException {
        List<Map<String,Object>> sheetData = new ArrayList();
        ArrayList<String> header = new ArrayList();
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();

        //Checking its not a empty sheet and processing the first row which is the header row
        // It is assumed that header is all Strings
        if (lastRowNum>0) {
            XSSFRow row = sheet.getRow(0);
            int colCount = row.getPhysicalNumberOfCells();
            for(int i=0; i<colCount ; i++){
                XSSFCell cell = row.getCell(i);
                if (cell.getCellType() == CellType.STRING) {
                    header.add(cell.getStringCellValue());
                }
            }
        }

        //Following is for processing from 2nd row which is actually the Test data
        //It also creates a list of map with key as header and value from each row
        for(int j=1; j<=lastRowNum; j++) {
            Map map = new HashMap();
            XSSFRow row = sheet.getRow(j);
            for(int k=0; k<header.size() ; k++) {
                XSSFCell cell = row.getCell(k);
                switch (cell.getCellType()){
                    case STRING:
                        map.put(header.get(k),cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        map.put(header.get(k),cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        map.put(header.get(k),cell.getBooleanCellValue());
                        break;
                    case _NONE:
                        map.put(header.get(k),null);
                        break;
                    case BLANK:
                        map.put(header.get(k),"");
                        break;
                }
            }
            sheetData.add(map);
        }
        return sheetData.toArray();
    }
}
