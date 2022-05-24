import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
}
