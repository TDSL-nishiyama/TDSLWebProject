package control.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import constents.Const.Common;

/**
 * {@index データベースアクセス用インターフェース}
 * @see 設定内容はプロパティファイル。格納場所はconstents.Constクラスで指定
 */
public interface DBAccess {

  /**
   * {@index JDBCの読み込みを行う}
   */
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

  /**
   * {@index データベースへの接続を行う}
   * @param conn Connection newでも生きてるやつでも
   * @return 引数で指定したConnection
   */
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

  /**
   * {@index データベースの切断を行う}
   * @param conn Connection 切断したいConnection
   */
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
