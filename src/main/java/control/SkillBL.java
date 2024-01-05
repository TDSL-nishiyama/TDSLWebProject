package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
    List<Object> statement = new ArrayList<>();
    CastCommon castC = new CastCommon();

    //ステートメントの設定
    statement.add(selId);

    SkillDAO dao = new SkillDAO();

    //基本情報の取得
    List<Object> userInfo = new ArrayList<>();
    userInfo = dao.getUserInfo(selId);
    String userName = userInfo.get(0).toString();
    //年次の変換
    String nenji = setNenji(castC.chgStrtoLD(userInfo.get(1).toString()));

    //資格情報の取得
    List<SkillEntity> resultDB1 = new ArrayList<SkillEntity>();
    String sikaku1YMD = "";
    String sikaku1 = "";
    String sikaku2YMD = "";
    String sikaku2 = "";
    String sikaku3YMD = "";
    String sikaku3 = "";
    resultDB1 = dao.selectSkillSikaku("skill\\selectSkill.sql", statement);
    
    if (!(resultDB1.isEmpty())) {     
      sikaku1YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB1.get(0).getSikaku1YMD()));
      sikaku1 = resultDB1.get(0).getSikaku1();
      sikaku2YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB1.get(0).getSikaku2YMD()));
      sikaku2 = resultDB1.get(0).getSikaku2();
      sikaku3YMD = castC.nullToBlank(castC.chgLDtoStr(resultDB1.get(0).getSikaku3YMD()));
      sikaku3 = resultDB1.get(0).getSikaku3();
    }

    //職歴情報の取得
    List<SkillEntity> resultDB2 = new ArrayList<SkillEntity>();
    statement.add(selId);//skill_2テーブル用
    statement.add(selId);//skill_3テーブル用
    resultDB2 = dao.selectSkillCarrier("skill\\selectSkillUnion.sql", statement);
    
    //職歴情報の分だけループ（1-3想定だけどテーブル増やせば増やせる）
    for (int i = 0; i < resultDB2.size(); i++) {
      //職歴情報のDB取得結果がある場合はそれぞれ値を設定。ない場合は基本情報・資格情報を除き空白を設定して返却
      if (!(resultDB2.get(0).getC1SYMD() == null)) {
        //期間の設定
        String c1kikanView = getKikan(resultDB2.get(i).getC1SYMD(), resultDB2.get(i).getC1EYMD());
        String c2kikanView = getKikan(resultDB2.get(i).getC2SYMD(), resultDB2.get(i).getC2EYMD());
        String c3kikanView = getKikan(resultDB2.get(i).getC3SYMD(), resultDB2.get(i).getC3EYMD());

        //日付項目はString型（yyyy-mm-dd形式）に変更のうえNULLの場合空白に設定
        String c1sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC1SYMD()));
        String c1eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC1EYMD()));
        String c2sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC2SYMD()));
        String c2eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC2EYMD()));
        String c3sYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC3SYMD()));
        String c3eYMD = castC.nullToBlank(castC.chgLDtoStr(resultDB2.get(i).getC3EYMD()));

        SkillBean bean = new SkillBean(
            selId, userName, nenji,
            sikaku1, sikaku1YMD,
            sikaku2, sikaku2YMD,
            sikaku3, sikaku3YMD,
            c1sYMD, c1eYMD, c1kikanView,
            castC.chgKaigyouCode(resultDB2.get(i).getCarrier1()), castC.chgKaigyouCode(resultDB2.get(i).getC1pos()),
            castC.chgKaigyouCode(resultDB2.get(i).getC1tech()),
            c2sYMD, c2eYMD, c2kikanView,
            castC.chgKaigyouCode(resultDB2.get(i).getCarrier2()), castC.chgKaigyouCode(resultDB2.get(i).getC2pos()),
            castC.chgKaigyouCode(resultDB2.get(i).getC2tech()),
            c3sYMD, c3eYMD, c3kikanView,
            castC.chgKaigyouCode(resultDB2.get(i).getCarrier3()), castC.chgKaigyouCode(resultDB2.get(i).getC3pos()),
            castC.chgKaigyouCode(resultDB2.get(i).getC3tech()));

        result.add(bean);
        //資格は1回しか表示させたくないので2回目以降はNULL
        sikaku1 = null;
        sikaku1YMD = null;
        sikaku2 = null;
        sikaku2YMD = null;
        sikaku3 = null;
        sikaku3YMD = null;
        
      } else {
        SkillBean bean = new SkillBean(selId, userName, nenji, sikaku1, sikaku1YMD, sikaku2, sikaku2YMD, sikaku3,sikaku3YMD, "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        result.add(bean);
      }

    }

    return result;
  }

  /**
   * {@index スキルシート更新処理}
   * @param selId
   * @param gamenInfo
   * @throws SQLException
   */
  public void updSkill(int selId, List<Map<String, Object>> gamenInfo) throws SQLException {
    //共通クラスのインスタンス化
    SkillDAO dao = new SkillDAO();

    //IDがある場合UPDATE、ない場合INSERT
    if (dao.checkIdInSkill("\\skill\\checkIdInSkill_1.sql",selId) == true) {
      dao.updSkil("\\skill\\updateSkill_1.sql",gamenInfo.get(0));
    } else {
      dao.insSkil("\\skill\\insertSkill_1.sql",gamenInfo.get(0));
    }
    //職歴No4以降が存在する場合、UPDATE/INSERT
    if(!(gamenInfo.get(1).isEmpty())) {
      if (dao.checkIdInSkill("\\skill\\checkIdInSkill_2.sql",selId) == true) {
        dao.updSkil("\\skill\\updateSkill_2.sql",gamenInfo.get(1));
      } else {
        dao.insSkil("\\skill\\insertSkill_2.sql",gamenInfo.get(1));
      }
    }
    //職歴No7以降が存在する場合、UPDATE/INSERT
    if(!(gamenInfo.get(1).isEmpty())) {
      if (dao.checkIdInSkill("\\skill\\checkIdInSkill_3.sql",selId) == true) {
        dao.updSkil("\\skill\\updateSkill_3.sql",gamenInfo.get(2));
      } else {
        dao.insSkil("\\skill\\insertSkill_3.sql",gamenInfo.get(2));
      }
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
    sb.append(kikan.getMonths() + 1);
    sb.append("ヵ月");

    return sb.toString();
  }
}