package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindUserTables {
  public static void main(String[] args) throws SQLException {
    // Connect to the database
    Connection connection =
        DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
    System.out.println("Database connected");

    DatabaseMetaData databaseMetaData = connection.getMetaData();

    ResultSet rsTables = databaseMetaData.getTables(null, null, null, new String[] {"TABLE"});
    System.out.println("User tables: ");
    while (rsTables.next()) {
      System.out.println(rsTables.getString("TABLE_NAME") + " ");
    }

    // Close the connection
    connection.close();
  }
}
