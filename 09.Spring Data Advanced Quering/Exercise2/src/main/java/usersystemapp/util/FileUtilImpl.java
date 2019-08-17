package usersystemapp.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {


    @Override
    public List<String> getFileInput(String filePath) {

        File file = new File(filePath);

        BufferedReader reader = null;
        String line = "";
        List<String> tokens = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tokens = reader != null
                ? reader.lines().collect(Collectors.toList())
                : null;

        return tokens;
    }
}
