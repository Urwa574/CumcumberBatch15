package DBTest;

import java.sql.*;

public class DataBaseTest {
    public static void main(String[] args) {
        //we use main here bcz we are using java here
    /* to build the connection with the database
    we need 3 things, URL, username, password
     */
        String urlDB = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String usernameDB = "syntax_hrm";
        String passwordDB = "syntaxhrm123";

        try {
            //we need to establish the connection to the database
            Connection conn =  DriverManager.getConnection(urlDB,usernameDB,passwordDB);//To establish connection
            System.out.println("connection is created for batch 15");
            //create a statement to send sql queries
            Statement statement= conn.createStatement();
            //when we send any query to the database then db returns
            //resultSet (tables with rows and columns)
           ResultSet rSet= statement.executeQuery("select FirstName, LastName from person");
           rSet.next();

            String fName = rSet.getString("FirstName");
            String lName = rSet.getString("LastName");
            System.out.println(fName + " " + lName);
            rSet.next();

           fName = rSet.getString("FirstName");
            lName = rSet.getString("LastName");
            System.out.println(fName + " " + lName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
