package com.company;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        //Lab: Database Apps Introduction
        /*
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);


        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni?useSSL=false", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        PreparedStatement stmt1 =
                connection.prepareStatement("SELECT * FROM employees");

//        String salary = sc.nextLine();
//        stmt.setDouble(1, Double.parseDouble(salary));
        //ResultSet rs = stmt.executeQuery();
        ResultSet rs = stmt1.executeQuery();

        while (rs.next()) {
            System.out.printf("%s  %s",
                    rs.getString("first_name") + " " + rs.getString("last_name"),
                    System.lineSeparator() +
                            rs.getString("job_title"));
        }
        connection.close();
        */
        //******************************************************************************************

        //Exercises: Introduction to DB Apps
        /*
        BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
        String user = "";
        System.out.print("Enter username default (root): ");
        user = stream.readLine();
        user = user.equals("") ? "root" : user;

        System.out.print("Enter password default (empty):");
        String password = "";
        password = stream.readLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni?useSSL=false", props);

        //SQL execution is done via the Statement Interface. To prevent SQL Injection, PreparedStatement is being used:
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
        String salary = stream.readLine().trim();
        stmt.setInt(1, Integer.parseInt(salary));


        ResultSet resultSet = stmt.executeQuery();
        String space = " ";
        while (resultSet.next()) {
            System.out.printf("%s %s %s %s",
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("employee_id"),
                    System.lineSeparator());
        }

        connection.close();
    */
        //******************************************************************************************

        String password = "";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db?useSSL=false", props);

        Engine engine = new Engine(connection);
        engine.run();
    }

/*
    //*********************************************************************
    //task 3 and 4
    private static void getVillainNames() throws SQLException {
        String query = "select v.name, count(mv.minion_id) from minions_villains as mv join villains as v on mv.villain_id = v.id group by v.name having count(mv.minion_id) > ? order by count(mv.minion_id) desc;";
        PreparedStatement allVillainsNames = connection.prepareStatement(query);
        allVillainsNames.setInt(1, 15);
        ResultSet resultSet = allVillainsNames.executeQuery();

        while (resultSet.next())
            System.out.println(String.format("%s %d"
                    , resultSet.getString(1)
                    , resultSet.getInt(2)));
    }

    private static void GetMinionNamesForVillainId() throws SQLException, IOException {
        String mainQuery = "select v.name, m.name, m.age from minions_villains as mv join villains as v on mv.villain_id = v.id join minions as m on mv.minion_id = m.id where mv.villain_id = ?";
        String suqueryQuery = "select v.name from villains as v where v.id = ?;";
        String input = stream.readLine();

        PreparedStatement villainMain = connection.prepareStatement(mainQuery);
        villainMain.setInt(1, Integer.parseInt(input));
        ResultSet minionNamesForVillainId = villainMain.executeQuery();

        PreparedStatement villainNameStmt = connection.prepareStatement(suqueryQuery);
        villainNameStmt.setInt(1, Integer.parseInt(input));
        ResultSet villainNameRs = villainNameStmt.executeQuery();

        villainNameRs.next();
        System.out.println(villainNameRs.getString(1));

        while (minionNamesForVillainId.next()) {
            System.out.println(String.format("%s %d", minionNamesForVillainId.getString(2)
                    , minionNamesForVillainId.getInt(3)));
        }
    }
    //**********************************************************************************
*/
}