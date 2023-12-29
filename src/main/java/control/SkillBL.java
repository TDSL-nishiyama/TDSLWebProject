package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import control.common.CalcCommon;
import control.common.CastCommon;
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
    CastCommon castC = new CastCommon();

    fileName = "skill\\selectSkill.sql";
    statement.add(selId);

    SkillDAO dao = new SkillDAO();
    List<SkillEntity> resultDB = new ArrayList<SkillEntity>();
    resultDB = dao.selectSkill(fileName, statement);

    //レコードがある時ないときで分岐
    if (!(resultDB.isEmpty())) {
      //期間の設定
      String c1kikanView = getKikan(resultDB.get(0).getC1SYMD(), resultDB.get(0).getC1EYMD());
      String c2kikanView = getKikan(resultDB.get(0).getC2SYMD(), resultDB.get(0).getC2EYMD());
      String c3kikanView = getKikan(resultDB.get(0).getC3SYMD(), resultDB.get(0).getC3EYMD());

      //日付項目はString型（yyyy-mm-dd形式）に変更のうえNULLの場合空白に設定
      String sikaku1YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getSikaku1YMD()));
      String sikaku2YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getSikaku2YMD()));
      String sikaku3YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getSikaku3YMD()));
      String c1sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC1SYMD()));
      String c1eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC1EYMD()));
      String c2sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC2SYMD()));
      String c2eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC2EYMD()));
      String c3sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC3SYMD()));
      String c3eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB.get(0).getC3EYMD()));

      SkillBean bean = new SkillBean(
          resultDB.get(0).getUserId(), resultDB.get(0).getUserName(), setNenji(resultDB.get(0).getNyuusyaYMD()),
          resultDB.get(0).getSikaku1(), sikaku1YMD,
          resultDB.get(0).getSikaku2(), sikaku2YMD,
          resultDB.get(0).getSikaku3(), sikaku3YMD,
          c1sYMD, c1eYMD, c1kikanView,
          castC.chgKaigyouCode(resultDB.get(0).getCarrier1()), castC.chgKaigyouCode(resultDB.get(0).getC1pos()),
          castC.chgKaigyouCode(resultDB.get(0).getC1tech()),
          c2sYMD, c2eYMD, c2kikanView,
          castC.chgKaigyouCode(resultDB.get(0).getCarrier2()), castC.chgKaigyouCode(resultDB.get(0).getC2pos()),
          castC.chgKaigyouCode(resultDB.get(0).getC2tech()),
          c3sYMD, c3eYMD, c3kikanView,
          castC.chgKaigyouCode(resultDB.get(0).getCarrier3()), castC.chgKaigyouCode(resultDB.get(0).getC3pos()),
          castC.chgKaigyouCode(resultDB.get(0).getC3tech()));
      result.add(bean);

    } else {
      List<Object> userInfo = new ArrayList<>();
      
      userInfo = dao.getUserInfo(selId);
      
      SkillBean bean = new SkillBean(selId, (String)userInfo.get(0),castC.chgDateToStr((Date) userInfo.get(1)), "","","","","", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","");
      result.add(bean);
    }

    return result;
  }

  /**
   * {@index スキルシート更新処理}
   * @param selId
   * @param gamenInfo
   * @throws SQLException
   */
  public void updSkill(int selId, Map<String, Object> gamenInfo) throws SQLException {
    //共通クラスのインスタンス化
    SkillDAO dao = new SkillDAO();

    //IDがある場合UPDATE、ない場合INSERT
    if (dao.checkIdInSkill(selId) == true) {
      dao.updSkil(gamenInfo);
    } else {
      dao.insSkil(gamenInfo);
    }
  }

  /**
   * {@index 経験年数の設定}
   * @param nyuusyaYMD
   * @return
   */
  private String setNenji(LocalDate nyuusyaYMD) {
    if (nyuusyaYMD == null) {
      return "";
    }

    String result = null;
    CalcCommon calc = new CalcCommon();
    Period prNyuusyaYMD = calc.diffDate(nyuusyaYMD);
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
  private String getKikan(LocalDate date1, LocalDate date2) {
    if (date1 == null || date2 == null) {
      return "";
    }

    CalcCommon calcC = new CalcCommon();
    Period kikan = calcC.diffDate(date1, date2);
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