package bookshopsystemapp.util;

import bookshopsystemapp.domain.entities.models.Category;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] getFileContent(String filePath) {
        File file = new File(filePath);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        List<String> tokens = new ArrayList<>();

        tokens = reader.lines().collect(Collectors.toList());

        return tokens.stream()
                .filter(el -> !el.isEmpty())
                .toArray(String[]::new);

    }
}
