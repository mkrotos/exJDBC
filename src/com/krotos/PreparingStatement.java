package com.krotos;

import java.sql.*;

public class PreparingStatement {
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
            // 2. Prepare a statement
            myStmt = myConn.prepareStatement("select * from employees where salary>? and department=?");
            // 3. Set the parameters
            ((PreparedStatement) myStmt).setDouble(1, 60000);
            ((PreparedStatement) myStmt).setString(2, "Engineering");
            // 4. Execute query
            myRs = ((PreparedStatement) myStmt).executeQuery();
            // 5. Display
            display(myRs);


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

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.0f, %s\n", lastName, firstName, salary, department);
        }
    }
}
