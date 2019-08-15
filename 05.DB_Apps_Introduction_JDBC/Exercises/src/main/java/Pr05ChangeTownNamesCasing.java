import constants.DBConnection;
import constants.Globals;
import constants.Messages;
import constants.SQLQueries;
import persistance.DatabaseManager;
import persistance.ParameterType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Pr05ChangeTownNamesCasing {
    public static void main(String[] args) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader((new InputStreamReader(System.in, Globals.DEFAULT_ENCODING)));
            System.out.println(Messages.ENTER_COUTRY);

            String token = reader.readLine().trim();

            Map<ParameterType, Object> parameters = new LinkedHashMap<>();
            parameters.put(ParameterType.STRING, token);

            try {
                List<Map<String, Object>> result = DatabaseManager.getInstance().executePreparedStatement(
                        DBConnection.URL_DATABASE,
                        null,
                        SQLQueries.GET_TOWNS_FOR_GIVEN_COUNTRY,
                        parameters);
                if (result.size() == 0) {
                    System.out.println(Messages.NO_TOWN_NAMES);
                    return;
                }

                for (Map<String, Object> stringObjectMap : result) {
                    System.out.print(stringObjectMap.values().toString().toUpperCase().trim());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
