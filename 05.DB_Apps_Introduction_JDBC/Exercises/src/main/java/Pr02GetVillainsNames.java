import constants.DBConnection;
import constants.Messages;
import constants.SQLQueries;
import persistance.DatabaseManager;
import view.ResultsParser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Pr02GetVillainsNames {

    public static void main(String[] args) {

        System.out.println(Messages.GET_VILLAINS_NAMES);

        System.out.println(getVillainsWithAtLeastThreeMinions());

        System.out.println();
    }

    private static String getVillainsWithAtLeastThreeMinions() {
        try {
            List<Map<String, Object>> result = DatabaseManager.getInstance().executeQuery(
                    DBConnection.URL_DATABASE,
                    null,
                    SQLQueries.SELECT_VILLAINS_WITH_MORE_THAN_THREE_MINIONS);

            return ResultsParser.getInstance()
                    .parseResults(result, true, Messages.SEPARATOR, 0, -1);

        } catch (SQLException e) {
            e.printStackTrace();
            return Messages.DATABASE_ERROR;
        }
    }
}
