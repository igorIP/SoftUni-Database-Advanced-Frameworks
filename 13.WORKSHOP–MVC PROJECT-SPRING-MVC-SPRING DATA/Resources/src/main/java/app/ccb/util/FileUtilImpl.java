package app.ccb.util;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(filePath))));

        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
