package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import control.common.CalcCommon;
import control.common.DAOCommon;
import control.common.DBAccess;
import jakarta.websocket.OnError;

public class TestBL extends DAOCommon implements DBAccess {

  public Object testBLMain() {

    Object result = null;

    result = calcationAveAge();

    return result;

  }

  public double calcationAveAge() {
    double result = 0.0;

    String userSeinenngappiGetSQL = "select S.seinenngappi from usershousai as S JOIN user as U ON S.id = U.id where U.del = '';";

    double userCnt = super.countSQL("test.sql", null);

    List<TestBean> resultDB = new ArrayList<>();
    List<String> column = new ArrayList<>();
    column.add("seinenngappi");

    resultDB = selectSQLTest(userSeinenngappiGetSQL, column);
    //    for(int i = 0;i < userCnt; i++) {
    //      result += setYear(resultDB.get(i).getSeinenngappi());
    //    }
    List<Double> list = new ArrayList<>();
    for (TestBean l : resultDB) {
      list.add((double) setYear(l.getSeinenngappi()));
    }
    Stream<Double> s = list.stream();
    var a = resultDB.iterator();
    
    return result;
  }

  private int setYear(Date seinenngappi) {

    int result = 0;
    Period prSeinenngappi = null;
    CalcCommon calc = new CalcCommon();
    prSeinenngappi = calc.diffDate(seinenngappi);
    result = prSeinenngappi.getYears();

    return result;
  }

  protected List<TestBean> selectSQLTest(String pSQL, List<String> column) {

    List<TestBean> result = new ArrayList<>();

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = pSQL;

    //クエリの発行・格納
    try {
      //発行
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      Date seinenngappi = null;

      //格納
      while (rs.next()) {
        seinenngappi = rs.getDate(column.get(0));
        TestBean bean = new TestBean(seinenngappi);
        result.add(bean);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      //DB切断
      DBAccess.super.closeDB(conn);
    }

    return result;
  }

}
