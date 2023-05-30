import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConn {
// local db
//  public static Connection make() throws SQLException {
//    return DriverManager.getConnection(
//        "jdbc:postgresql://localhost:5432/postgres",
//        "postgres",
//        "pg123456"
//    );
//  }
// Heroku db
  public static Connection make() throws SQLException {
    //Herocu
    return DriverManager.getConnection(
            "jdbc:postgresql://ec2-63-35-80-199.eu-west-1.compute.amazonaws.com:5432/d2lratfokqg2li",
            "vystzdynefyycp",
            "30eacd47b2d22cbc4e1a28d86d33d615eab8f99f4f12185e73e76a058de7c7c2"
    );
    //MySQL
//    return DriverManager.getConnection(
//            "jdbc:mysql://sc500381.mysql.tools:3306/sc500381_users",
//            "sc500381_users",
//            "azcJ5-)9B7"
//    );


  }



}
