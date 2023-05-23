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
    return DriverManager.getConnection(
            "jdbc:postgresql://ec2-54-195-24-35.eu-west-1.compute.amazonaws.com:5432/dcif62h0akno24",
            "yanheoibydneaa",
            "fe8abe73cf010efffa1355864a889123cb7252c4876c9fa881ca6f9b400ab689"
    );
  }

}
