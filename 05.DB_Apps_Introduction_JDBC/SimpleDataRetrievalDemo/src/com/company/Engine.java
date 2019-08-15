package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Engine {

    private BufferedReader stream;
    private Connection connection;


    public Engine(Connection connection) {
        this.connection = connection;
        this.stream = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException, SQLException {
        System.out.println("Input:");
        String line1 = stream.readLine().trim();
        String line2 = stream.readLine().trim();

        List<String> tokensMinion = Arrays.asList(line1.split("\\s+"));
        List<String> tokensVillain = Arrays.asList(line2.split("\\s+"));

        String minionType = tokensMinion.get(0);
        String minionName = tokensMinion.get(1);
        Integer minionAge = Integer.parseInt(tokensMinion.get(2));
        String minionTown = tokensMinion.get(3);

        String villainType = tokensVillain.get(0);
        String villainName = tokensVillain.get(1);

        /*
         In case the town of the minion is not in the database insert it as well
         */
        if (!this.checkIfEntityExists(minionTown, "towns"))
            this.insertTown(minionTown);

        if (!this.checkIfEntityExists(villainName, "villains"))
            this.insertVillain(villainName);

        System.out.println(this.getEntityId("Igor", "villains"));
    }

    private int getEntityId(String name, String tableName) {
        String queryStringGetName = String.format("select id from %s where name like ?",tableName);
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = this.connection.prepareStatement(queryStringGetName);
            preparedStatement.setString(1, name);
            //preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void insertVillain(String villainName) {
        String evilnessFactor = "evil";
        String querySQL = String.format("insert into villains(name, evilness_factor) values(?, '%s')", evilnessFactor);
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(querySQL);
            preparedStatement.setString(1, villainName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTown(String minionTown) throws SQLException {
        String querySQL = String.format("insert into towns(name) values(?)");
        PreparedStatement preparedStatement = this.connection.prepareStatement(querySQL);
        preparedStatement.setString(1, minionTown);
        preparedStatement.executeUpdate();
    }

    /*
    get minion town
    */
    private boolean checkIfEntityExists(String name, String tableName) {
        String querySQL = String.format("select * from %s where name like ?", tableName);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = this.connection.prepareStatement(querySQL);
            //preparedStatement.setString(1, tableName);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getString(2).equals(name))
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
