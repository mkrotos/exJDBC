package com.krotos;

import java.sql.*;

public class JDBCUpdateDemo {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://localhost:3306/demo";
        String noSSL = "?autoReconnect=true&useSSL=false";
        String user = "student";
        String pass = "student";

        try {
            // 1. Get connection
            myConn = DriverManager.getConnection(dbUrl + noSSL, user, pass);
            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Change email of John
            System.out.println("Changing email \n");


            int rowsAffected = myStmt.executeUpdate(
                    "update employees " +
                            "set email='john.doe@luv2code.com' " +
                            " where last_name='Doe' and first_name='John'"
            );
            // 4. Verify
            myRs = myStmt.executeQuery("select * from employees where first_name='John' and last_name='Doe'");
            // 5. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name")+", "+myRs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}



