package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constents.Const.Common;
import constents.SkillConst.Skill;
import constents.Table.User;
import constents.UserShousai;
import control.common.CastCommon;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.SkillEntity;

public class SkillDAO extends DAOCommon implements DBAccess {

  private String sqlPath = Common.SQL_FILE_PATH;

  /**
   * {@index skillテーブル取得}
   * @param fileName
   * @param statement　ない場合NULLを指定
   * @return
   * @throws SQLException
   */
  public List<SkillEntity> selectSkill(String fileName, List<Object> statement)
      throws SQLException {

    List<SkillEntity> result = new ArrayList<SkillEntity>();

    sqlPath += fileName;
    CastCommon castCommon = new CastCommon();

    //格納用変数の宣言
    int userId;
    String userName;
    LocalDateTime nyuusyaYMD = null;
    String sikaku1;
    LocalDateTime sikaku1YMD = null;
    String sikaku2;
    LocalDateTime sikaku2YMD = null;
    String sikaku3;
    LocalDateTime sikaku3YMD = null;
    LocalDateTime c1SYMD = null;
    LocalDateTime c1EYMD = null;
    String carrier1;
    String c1pos;
    LocalDateTime c2SYMD = null;
    LocalDateTime c2EYMD = null;
    String carrier2;
    String c2pos;
    LocalDateTime c3SYMD = null;
    LocalDateTime c3EYMD = null;
    String carrier3;
    String c3pos;

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
      //日付項目(MySQL:DateTime)はDateからLocalDateTimeに変換して格納
      //Date型でキャストしないとjava.sql.toInstant()が呼ばれてUnsupportedOperationExceptionが発生するため
      while (rs.next()) {
        userId = rs.getInt(Skill.COL_USERID);
        userName = rs.getString(User.COL_USERNAME);
        if (rs.getDate(UserShousai.COL_NYUUSYAYMD) != null) {
          nyuusyaYMD = castCommon.chgDtoLD(new Date(rs.getDate(UserShousai.COL_NYUUSYAYMD).getTime()));
        }
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
        if (rs.getDate(Skill.COL_CARRIER1_START) != null) {
          c1SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER1_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER1_END) != null) {
          c1EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER1_END).getTime()));
        }
        carrier1 = rs.getString(Skill.COL_CARRIER1);
        c1pos = rs.getString(Skill.COL_CARRIER1_POS);
        if (rs.getDate(Skill.COL_CARRIER2_START) != null) {
          c2SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER2_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER2_END) != null) {
          c2EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER2_END).getTime()));
        }
        carrier2 = rs.getString(Skill.COL_CARRIER2);
        c2pos = rs.getString(Skill.COL_CARRIER2_POS);
        if (rs.getDate(Skill.COL_CARRIER3_START) != null) {
          c3SYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER3_START).getTime()));
        }
        if (rs.getDate(Skill.COL_CARRIER3_END) != null) {
          c3EYMD = castCommon.chgDtoLD(new Date(rs.getDate(Skill.COL_CARRIER3_END).getTime()));
        }
        carrier3 = rs.getString(Skill.COL_CARRIER3);
        c3pos = rs.getString(Skill.COL_CARRIER3_POS);

        SkillEntity entity = new SkillEntity(userId, userName,nyuusyaYMD,sikaku1, sikaku1YMD, sikaku2, sikaku2YMD, sikaku3, sikaku3YMD,
            c1SYMD, c1EYMD, carrier1, c1pos, c2SYMD, c2EYMD, carrier2, c2pos, c3SYMD, c3EYMD, carrier3, c3pos);
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
}
