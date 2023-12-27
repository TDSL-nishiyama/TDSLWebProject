package control;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import control.common.CalcCommon;
import model.SkillBean;
import model.SkillEntity;

public class SkillBL {
  
  /**
   * {@index スキルシートに表示する値を取得する}
   * @param selId
   * @return
   * @throws SQLException
   */
  public List<SkillBean> getSkill(int selId) throws SQLException {
    List<SkillBean> result = new ArrayList<SkillBean>();
    String fileName = null;
    List<Object> statement = new ArrayList<>();

    fileName = "skill\\selectSkill.sql";
    statement.add(selId);

    SkillDAO dao = new SkillDAO();
    List<SkillEntity> resultDB = new ArrayList<SkillEntity>();
    resultDB = dao.selectSkill(fileName, statement);

    //期間の設定
    String c1kikanView = getKikan(resultDB.get(0).getC1SYMD(), resultDB.get(0).getC1EYMD());
    String c2kikanView = getKikan(resultDB.get(0).getC2SYMD(), resultDB.get(0).getC2EYMD());
    String c3kikanView = getKikan(resultDB.get(0).getC3SYMD(), resultDB.get(0).getC3EYMD());

    SkillBean bean = new SkillBean(
        resultDB.get(0).getUserId(),resultDB.get(0).getUserName(),setNenji(resultDB.get(0).getNyuusyaYMD()),
        resultDB.get(0).getSikaku1(), resultDB.get(0).getSikaku1YMD(),
        resultDB.get(0).getSikaku2(), resultDB.get(0).getSikaku2YMD(),
        resultDB.get(0).getSikaku3(), resultDB.get(0).getSikaku3YMD(),
        resultDB.get(0).getC1SYMD(), resultDB.get(0).getC1EYMD(), c1kikanView, resultDB.get(0).getCarrier1(), resultDB.get(0).getC1pos(),
        resultDB.get(0).getC2SYMD(), resultDB.get(0).getC2EYMD(), c2kikanView, resultDB.get(0).getCarrier2(), resultDB.get(0).getC2pos(),
        resultDB.get(0).getC3SYMD(), resultDB.get(0).getC3EYMD(), c3kikanView, resultDB.get(0).getCarrier3(), resultDB.get(0).getC3pos()
        );
    result.add(bean);

    return result;
  }
  
  /**
   * {@index 経験年数の設定}
   * @param nyuusyaYMD
   * @return
   */
  private String setNenji(LocalDateTime nyuusyaYMD) {
    if(nyuusyaYMD == null) {
      return "";
    }
    
    String result = null;
    Period prNyuusyaYMD = null;
    CalcCommon calc = new CalcCommon();
    prNyuusyaYMD = calc.diffDate(nyuusyaYMD);
    StringBuilder nenji = new StringBuilder();
    nenji.append(prNyuusyaYMD.getYears());
    nenji.append("年");
    nenji.append(prNyuusyaYMD.getMonths());
    nenji.append("ヵ月");

    result = nenji.toString();

    return result;
  }
  
  /**
   * {@index プロジェクト従事期間の設定}
   * @param date1 開始
   * @param date2 終了
   * @return
   */
  private String getKikan(LocalDateTime date1, LocalDateTime date2) {
    if(date1 == null || date2 == null) {
      return "";
    }

    CalcCommon calcC = new CalcCommon();
    Period kikan = calcC.diffDate(date2, date1);
    StringBuilder sb = new StringBuilder();
    if (kikan.getYears() != 0) {
      sb.append(kikan.getYears());
      sb.append("年");
    }
    sb.append(kikan.getMonths());
    sb.append("ヵ月");

    return sb.toString();
  }
}