package com.pckoala.javafx.chapter34;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

/**
 * Debugging: <br>
 * Log in to mysql root in your terminal and add <br>
 * <code>SET GLOBAL log_bin_trust_function_creators = 1;</code> <br>
 * and grant scott these permissions: <br>
 * <code>GRANT CREATE ROUTINE, ALTER ROUTINE on javabook.* TO 'scott'@'localhost';</code> <br><br>
 * Code added to MySQL Workbench > scott > javabook > Functions > Create Function:
 *
 * <p><code>
 *   CREATE DEFINER=`scott`@`localhost` FUNCTION `studentFound`(first varchar(20), last varchar(20))
 *   <br>RETURNS int
 *   <br>DETERMINISTIC
 *   <br>BEGIN
 *   <br>declare result int;
 *   <br>select count(*) into result
 *   <br>from Student
 *   <br>where Student.firstName = first and
 *   <br>Student.lastName = last;
 *   <br>RETURN result;
 *   <br>END
 *   </code>
 *   </p>
 */
public class TestCallableStatement {
  public static void main(String[] args) throws Exception {
    Connection connection =
        DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");

    // Create a callable statement
    CallableStatement callableStatement = connection.prepareCall("{? = call studentFound(?, ?)}");

    Scanner input = new Scanner(System.in);
    System.out.print("Enter student's first name: ");
    String firstName = input.nextLine();
    System.out.print("Enter student's last name: ");
    String lastName = input.nextLine();

    callableStatement.setString(2, firstName); // sets IN parameter
    callableStatement.setString(3, lastName); // sets IN parameter
    callableStatement.registerOutParameter(1, Types.INTEGER); // register OUT parameter
    callableStatement.execute();

    if (callableStatement.getInt(1) >= 1) {
      System.out.println(firstName + " " + lastName + " is in the database.");
    } else {
      System.out.println(firstName + " " + lastName + " is not in the database");
    }
  }
}
