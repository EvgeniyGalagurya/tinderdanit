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
            "jdbc:postgresql://ec2-52-215-209-64.eu-west-1.compute.amazonaws.com:5432/d3fh8e2r21bk9k",
            "ceivqgqmbrerxy",
            "7d2824755478094e3f156bc3b9b992b8461baf1162788b147b391ef16eabb22d"
    );
  }

}
