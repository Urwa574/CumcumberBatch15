package DBTest;

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.Statement;
 import java.sql.*;
 import java.util.ArrayList;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 public class DataBaseTestThree {
    public static void main(String[] args) {
        String urlDB = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String usernameDB = "syntax_hrm";
        String passwordDB = "syntaxhrm123";

        try {
            Connection conn = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            System.out.println("Connection is created for batch 15");
            //create a statement to send sql queries
            Statement statement = conn.createStatement();
            String query = "select * from person;";
            ResultSet rset = statement.executeQuery(query);
            ResultSetMetaData rsmdata = rset.getMetaData();
           //extract data from resultset and store it in java data structure
            List<Map<String, String>> listFromRset = new ArrayList<>();
            //iterate through the rows
            while (rset.next()){
                Map<String, String> map = new LinkedHashMap<>();
                //iterate over columns
                for (int i=1; i<=rsmdata.getColumnCount(); i++){
                    //fetching key and value from the columns
                    String key = rsmdata.getColumnName(i);
                    String value = rset.getString(key);
                    map.put(key, value);
                }
                System.out.println(map);
                listFromRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
