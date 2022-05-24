import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

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

    public Object[] readFromExcel(String fileName, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        Workbook workbook = new XSSFWorkbook(file);



    }
}
