package control.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import constents.Const.Common;

public interface DBAccess {
	//JDBC読み込み
	default public void loadJDBCDriver() {

		LoadFile loadFile = new LoadFile();
		//プロパティファイルからドライバ読み込みに必要な情報を取得
		final String DRIVERNAME = loadFile.getProItem(Common.DB_PROP_PATH, Common.DRIVER_NAME);

		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//DB接続
	default public Connection connectionDB(Connection conn) {
		try {
			LoadFile loadFile = new LoadFile();
			//プロパティファイルからDBアクセスに必要な情報を取得
			String[] accessInfoName = { Common.JDBC_URL, Common.DB_USER, Common.DB_PASS };
			Map<String, String> accessInfo = new HashMap<String, String>();
			accessInfo = loadFile.getProItem(Common.DB_PROP_PATH, accessInfoName);

			//データベースへ接続
			conn = DriverManager.getConnection(accessInfo.get(Common.JDBC_URL),
					accessInfo.get(Common.DB_USER), accessInfo.get(Common.DB_PASS));

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return conn;
	}

	//DB切断
	default public void closeDB(Connection conn) {
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
