package com.krotos;

import java.sql.*;

public class JDBCDeleteDemo {

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
            System.out.println("Deleting \n");

            // 4.Delete all rows with Kazik Seba
            int rowsAffected = myStmt.executeUpdate(
                    "delete from employees " +
                            " where last_name='Kazik' and first_name='Seba'"
            );
            // 4. Verify
            myRs = myStmt.executeQuery("select * from employees");
            // 5. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name") + ", " + myRs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing
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
