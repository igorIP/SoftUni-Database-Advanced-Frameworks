package usersystemapp.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleUtilImpl implements ConsoleUtil {

    @Override
    public List<String> getConsoleInput() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        List<String> tokens = new ArrayList<>();
        String line = "";
        while (true) {
            try {
                if ((line = consoleReader.readLine()) == null) break;
            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }

            tokens.add(line);
        }
        return null;
    }
}
