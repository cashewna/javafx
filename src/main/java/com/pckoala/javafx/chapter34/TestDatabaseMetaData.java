package com.pckoala.javafx.chapter34;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseMetaData {
  public static void main(String[] args) throws SQLException {
    // Connect to the database
    Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost/javabook", "scott", "tiger"
    );
    System.out.println("Database connected");

    DatabaseMetaData databaseMetaData = connection.getMetaData();
    System.out.println("database URL: " + databaseMetaData.getURL());
    System.out.println("database username: " + databaseMetaData.getUserName());
    System.out.println("database product name: " + databaseMetaData.getDatabaseProductName());
    System.out.println("database product version: " + databaseMetaData.getDatabaseProductVersion());
    System.out.println("JDBC driver name: " + databaseMetaData.getDriverName());
    System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
    System.out.println("JDBC driver major version: " + databaseMetaData.getDriverMajorVersion());
    System.out.println("JDBC driver minor version: " + databaseMetaData.getDriverMinorVersion());
    System.out.println("Max number of connections: " + databaseMetaData.getMaxConnections());
    System.out.println("MaxTableNameLength: " + databaseMetaData.getMaxTableNameLength());
    System.out.println("MaxColumnsInTable: " + databaseMetaData.getMaxColumnsInTable());

    // Close the connection
    connection.close();
  }
}
