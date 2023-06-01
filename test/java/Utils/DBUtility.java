package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    static Connection conn = null;
    static Statement statement = null; // if u don't provide anything its actually null according to the industry standard
    private static ResultSet rset;// writing null is standard don't leave ; like this write null
    private static ResultSetMetaData resultSetMetaData;

    // This method create connection to DB, execute query and return object for resultset
    public static ResultSet getResultSet(String sqlQuery) {
        try {
            // here we are just establishing the connection instead of hard coding it
            conn = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("urldb"),
                    ConfigReader.getPropertyValue("usernamedb"),
                    ConfigReader.getPropertyValue("passworddb"));
            // make statement
            statement = conn.createStatement();

            //Return result set
            rset = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rset;

    }

    //This method is going to return the object or rsetmetadata
    public static ResultSetMetaData getResultSetMetaData(String query) {
        // method to get data
        rset = getResultSet(query);
        resultSetMetaData = null;
        // we use this line to get the data in tabular format so that we can use these in column keys and values for retrieval operation
        try {
            resultSetMetaData = rset.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSetMetaData;
    }

    //this method is extracting the data which will be stored in list of maps
    public static List<Map<String, String>> getListOfMapsFromRset(String query) {
        resultSetMetaData = getResultSetMetaData(query);
        List<Map<String, String>> listFromRset = new ArrayList<>();
        try {
            while (rset.next()) {
                Map<String, String> map = new LinkedHashMap<>();
                // iterate over columns
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

                    // now I need to print both keys and values
                    String key = resultSetMetaData.getColumnName(i);
                    // Remember value comes from rset NOT from resultSetMetadata
                    String value = rset.getString(key);

                    // to put keys and values in one place I need map for this, I already have Map declared already ,so I am going to use map.put(key,value);
                    map.put(key, value);

                    // after each iteration will give the first map then goes to the second row
                    // same scenario will be repeated until getting the last data

                }
                System.out.println(map);
                listFromRset.add(map); //must add in list otherwise it will override
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {  // block of code will execute everytime
            DBUtility.closeResultSet(rset);
            DBUtility.closeStatement(statement);
            DBUtility.closeConnection(conn);
        }
        return listFromRset;
    }
    ////Order to make connection //
    // connection
    //statement
    // resultset


    //First thing to close is : in reverse order
    //1- close resultset
    //2- close statement
    //3- close connection


    // This method is used to close resultset
    public static void closeResultSet(ResultSet rset) {
        if (rset != null) {
            try {
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // this is to close statement
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    // close connection method
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}












