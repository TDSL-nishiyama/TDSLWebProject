package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import constents.Const.Common;
import constents.SkillConst.Skill;
import constents.SkillConst.SkillHensyuuG;
import constents.Table.User;
import constents.UserShousai;
import control.common.CastCommon;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.SkillEntity;

public class SkillDAO extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@index 基本情報部分に表示する情報を取得}
   * @param selId
   * @return
   */
  public List<Object> getUserInfo(int selId) {
    List<String> column = new ArrayList<String>();
    column.add(User.COL_USERNAME);
    column.add(UserShousai.COL_NYUUSYAYMD);
    List<Object> statement = new ArrayList<Object>();
    statement.add(selId);

    return super.selectSQL("getUserInfo.sql", column, statement);
  }

  /**
   * {@index 取得資格部分に表示する情報を取得}
   * @param fileName
   * @param statement
   * @return
   * @throws SQLException
   */
  public List<SkillEntity> selectSkillSikaku(String fileName, List<Object> statement)
      throws SQLException {
    List<SkillEntity> result = new ArrayList<SkillEntity>();

    //SQLパスの指定
    sqlPath += fileName;

    //共通クラスのインスタンス化
    CastCommon castCommon = new CastCommon();

    //格納用変数
    String sikaku1 = "";
    LocalDate sikaku1YMD = null;
    String sikaku2 = "";
    LocalDate sikaku2YMD = null;
    String sikaku3 = "";
    LocalDate sikaku3YMD = null;

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    //クエリの発行・格納
    try {
      //発行
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      //ステートメントの設定
      if (statement != null) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }

      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      //格納
      //日付項目(MySQL:DateTime)はDateからLocalDateに変換して格納
      //Date型でキャストしないとjava.sql.toInstant()が呼ばれてUnsupportedOperationExceptionが発生するため
      while (rs.next()) {
        sikaku1 = rs.getString(Skill.COL_SIKAKU1);
        if (rs.getDate(Skill.COL_SIKAKU1YMD) != null) {
          sikaku1YMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_SIKAKU1YMD).getTime()));
        }
        sikaku2 = rs.getString(Skill.COL_SIKAKU2);
        if (rs.getDate(Skill.COL_SIKAKU2YMD) != null) {
          sikaku2YMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_SIKAKU2YMD).getTime()));
        }
        sikaku3 = rs.getString(Skill.COL_SIKAKU3);
        if (rs.getDate(Skill.COL_SIKAKU3YMD) != null) {
          sikaku3YMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_SIKAKU3YMD).getTime()));
        }
        SkillEntity entity = new SkillEntity(sikaku1, sikaku1YMD, sikaku2, sikaku2YMD, sikaku3, sikaku3YMD);
        result.add(entity);
      }
    } catch (SQLException e) {
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }
    return result;
  }

  /**
   * {@index 職歴部分に表示する情報を取得}
   * @param fileName
   * @param statement　ない場合NULLを指定
   * @return
   * @throws SQLException
   */
  public List<SkillEntity> selectSkillCarrier(String fileName, List<Object> statement)
      throws SQLException {

    List<SkillEntity> result = new ArrayList<SkillEntity>();

    sqlPath += fileName;
    CastCommon castCommon = new CastCommon();

    //格納用変数の宣言
    LocalDate c1SYMD = null;
    LocalDate c1EYMD = null;
    String carrier1 = "";
    String c1pos = "";
    String c1tech = "";
    LocalDate c2SYMD = null;
    LocalDate c2EYMD = null;
    String carrier2 = "";
    String c2pos = "";
    String c2tech = "";
    LocalDate c3SYMD = null;
    LocalDate c3EYMD = null;
    String carrier3 = "";
    String c3pos = "";
    String c3tech = "";

    //JDBC接続
    DBAccess.super.loadJDBCDriver();

    //DB接続
    Connection conn = null;
    conn = DBAccess.super.connectionDB(conn);

    //SQL文の作成
    String sql = null;
    sql = makeSQL(sqlPath);

    //クエリの発行・格納
    try {
      //発行
      PreparedStatement pStmt = conn.prepareStatement(sql.toString());
      //ステートメントの設定
      if (statement != null) {
        int cnt = statement.size(); //ステートメントを設定する数
        for (int i = 0; i < cnt; i++) {
          pStmt.setObject(i + 1, statement.get(i));
        }
      }

      //クエリの実行
      ResultSet rs = pStmt.executeQuery();

      //格納
      //日付項目(MySQL:DateTime)はDateからLocalDateに変換して格納
      //Date型でキャストしないとjava.sql.toInstant()が呼ばれてUnsupportedOperationExceptionが発生するため
      while (rs.next()) {
        if (rs.getDate(Skill.COL_CARRIER1_START) != null) {
          c1SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER1_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER1_END) != null) {
          c1EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER1_END).getTime()));
        }
        carrier1 = rs.getString(Skill.COL_CARRIER1);
        c1pos = rs.getString(Skill.COL_CARRIER1_POS);
        c1tech = rs.getString(Skill.COL_CARRIER1_TECH);
        if (rs.getDate(Skill.COL_CARRIER2_START) != null) {
          c2SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER2_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER2_END) != null) {
          c2EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER2_END).getTime()));
        }
        carrier2 = rs.getString(Skill.COL_CARRIER2);
        c2pos = rs.getString(Skill.COL_CARRIER2_POS);
        c2tech = rs.getString(Skill.COL_CARRIER2_TECH);
        if (rs.getDate(Skill.COL_CARRIER3_START) != null) {
          c3SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER3_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER3_END) != null) {
          c3EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER3_END).getTime()));
        }
        carrier3 = rs.getString(Skill.COL_CARRIER3);
        c3pos = rs.getString(Skill.COL_CARRIER3_POS);
        c3tech = rs.getString(Skill.COL_CARRIER3_TECH);

        SkillEntity entity = new SkillEntity(c1SYMD, c1EYMD, carrier1, c1pos, c1tech, c2SYMD, c2EYMD, carrier2, c2pos, c2tech, c3SYMD, c3EYMD,
            carrier3, c3pos, c3tech);
        result.add(entity);

        c1SYMD = null;
        c1EYMD = null;
        c2SYMD = null;
        c2EYMD = null;
        c3SYMD = null;
        c3EYMD = null;

      }
    } catch (SQLException e) {
      throw e;
    } finally {
      sqlPath = Common.SQL_FILE_PATH;
      //DB切断
      DBAccess.super.closeDB(conn);
    }
    return result;
  }

  /**
   * {@index skillテーブルのIDチェック用}
   * @param fileName SQLファイル名
   * @param selId　ユーザーID
   * @return true = IDあり false = IDなし
   */
  public boolean checkIdInSkill(String fileName, int selId) {
    boolean result = false;
    List<Object> statement = new ArrayList<Object>();

    statement.add(selId);

    if (super.countSQL(fileName, statement) == 1) {
      result = true;
    }

    return result;
  }

  /**
   * {@index skillテーブルに画面入力値をInsertする}
   * @param fileName SQLファイル名
   * @param pStatement
   * @throws SQLException
   */
  public void insSkil(String fileName, Map<String, Object> pStatement) throws SQLException {
    List<Object> statement = new ArrayList<Object>();
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_USERID));
    if (pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1) != null) {
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1YMD));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU2));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU2YMD));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU3));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU3YMD));
    }
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_TECH));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_TECH));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_TECH));
    statement.add(LocalDateTime.now());

    super.executeDML(fileName, statement);
  }

  /**
   * {@index skillテーブルを画面入力値を元にUPDATEする}
   * @param fileName SQLファイル名
   * @param pStatement
   * @throws SQLException
   */
  public void updSkil(String fileName, Map<String, Object> pStatement) throws SQLException {
    List<Object> statement = new ArrayList<Object>();
    //set句
    if (pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1) != null) {
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU1YMD));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU2));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU2YMD));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU3));
      statement.add(pStatement.get(SkillHensyuuG.GAMEN_SIKAKU3YMD));
    }
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER1_TECH));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER2_TECH));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_START));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_END));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_POS));
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_CARRIER3_TECH));
    statement.add(LocalDateTime.now());

    //where句
    statement.add(pStatement.get(SkillHensyuuG.GAMEN_USERID));

    super.executeDML(fileName, statement);
  }
}
