package com.krotos;

import java.sql.*;

public class JDBCInsertDemo {
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

            // 3. Insert a new employee
            System.out.println("Inserting a new employee to database \n");

            int rowsAffected = myStmt.executeUpdate(
                    "insert into employees " +
                            "(last_name, first_name, email, department, salary) " +
                            "values " +
                            "('Kazik', 'Seba', 'seba.kazik@mail.com','HR', 33000 )"
            );
            // 4. Verify
            myRs = myStmt.executeQuery("select * from employees order by last_name");
            // 5. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
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

