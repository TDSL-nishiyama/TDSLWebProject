package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

  private static String DRIVER_NAME = "";
  private static String JDBC_URL = "";
  private static String DB_USER = "";
  private static String DB_PASS = "";
  private static final String PATH = "C:\\pleiades\\2023-06\\workspace\\TDSLWebProject\\src\\main\\webapp\\WEB-INF\\lib\\DBAccess.properties";

  //DB読み込み用メソッド
  public static void loadJDBCDriver() {
    try {
      Properties properties = new Properties();
      FileInputStream inStream = null;
      try {
        inStream = new FileInputStream(PATH);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      try {
        properties.load(inStream);
      } catch (IOException e) {
        e.printStackTrace();
      }

      DRIVER_NAME = properties.getProperty("drivername");
      //JDBCドライバを読み込み
      Class.forName(DRIVER_NAME);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  //DB接続用メソッド
  public static Connection connectionDB(Connection conn) {
    try {
      Properties properties = new Properties();
      FileInputStream inStream = null;
      try {
        inStream = new FileInputStream(PATH);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      try {
        properties.load(inStream);
      } catch (IOException e) {
        e.printStackTrace();
      }

      JDBC_URL = properties.getProperty("jdbcurl");
      DB_USER = properties.getProperty("username");
      DB_PASS = properties.getProperty("userpassword");
      
      //データベースへ接続
      conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

    } catch (SQLException e) {
      e.printStackTrace();

    }
    return conn;
  }

  //DB切断用メソッド
  public static void closeDB(Connection conn) {
    //データベース切断
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
  }

}
