package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSetMetaData {
  public static void main(String[] args) throws SQLException {
    // Connect to the database
    Connection connection =
        DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
    System.out.println("Database connected");

    // Create a statement
    Statement statement = connection.createStatement();

    ResultSet resultSet = statement.executeQuery("select * from Enrollment");

    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      System.out.printf("%-12s\t", resultSetMetaData.getColumnName(i));
    }
    System.out.println();

    // Iterate through the result and print the students' names
    while (resultSet.next()) {
      for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
        System.out.printf("%-12s\t", resultSet.getObject(i));
      }
      System.out.println();
    }

    // Close the connection
    connection.close();
  }
}