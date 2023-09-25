package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	//DB読み込み用メソッド
	public static void loadJDBCDriver() {

		//プロパティファイルを読み込む
		Properties properties = new Properties();

		try {
			InputStream inStream = new FileInputStream("C:\\DBAccess.properties");
			properties.load(inStream);
//			properties.load(new FileInputStream("DBAccess.properties"));

			final String DRIVER_NAME = properties.getProperty("drivername");

			//JDBCドライバを読み込み
			Class.forName(DRIVER_NAME);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//DB接続用メソッド
	public static Connection connectionDB(Connection conn) {

		//プロパティファイルを読み込む
		Properties properties = new Properties();

		try {
			InputStream inStream = new FileInputStream("C:\\DBAccess.properties");
			properties.load(inStream);
//			properties.load(new FileInputStream("DBAccess.properties"));

			final String JDBC_URL = properties.getProperty("jdbcurl");
			final String DB_USER = properties.getProperty("username");
			final String DB_PASS = properties.getProperty("userpassword");

			//データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

		} catch (IOException e) {
			e.printStackTrace();
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
