package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckCommon {
  /**
   * {@index 日付整合性チェック}
   * @param pDate　String Dateとして想定された値
   * @return
   */
  public boolean checkDate(String pDate) {
    boolean result = true;

    //項目が存在しない場合チェックを行わない
    if (pDate == null || pDate.equals("")) {
      return result;
    }

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      sdf.setLenient(false);
      @SuppressWarnings("unused")
      Date date = sdf.parse(pDate);
      //日付変換エラー・パラメータ想定外エラーの場合エラーとする
    } catch (ParseException | IllegalArgumentException e) {
      result = false;
    }
    return result;

  }

  /**
   * {@index ブランクまたはNULLチェック}
   * @param str 文字列
   * @return true=エラーなし　false=エラーあり
   */
  public boolean checkBlankOrNULL(String str) {
    boolean result = true;

    if (str == null || str.trim().equals("")) {
      result = false;
    }

    return result;
  }

  /**
   * {@index ユーザーID存在確認（del項目に値があるユーザーを含まない）}
   * @param userId
   * @return
   */
  public int checkUserId(int userId) {

    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(userId);
    DAOCommon dao = new DAOCommon();
    result = dao.countSQL("checkUserId.sql", statement);

    return result;
  }

  /**
   * {@index ユーザーID存在確認（del項目に値があるユーザーを含む）}
   * @param userId
   * @return
   */
  public int checkUserIdAll(int userId) {

    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(userId);
    DAOCommon dao = new DAOCommon();
    result = dao.countSQL("checkUserIdAll.sql", statement);

    return result;
  }

  /**
   *{@index 必須項目確認} 
   * @param chkKoumoku チェックしたい項目名（対象画面情報のkey/value）
   * @param hissuKoumoku 必須項目名（KEY）/必須項目の論理名(VALUE）
   * @return key:errflg エラーの可否 true = 正常　false = 異常
   * @return key:errMsgKoumoku hissuKoumokuの中でエラーとなった項目
   */
  public Map<String, Object> checkHissu(Map<String, Object> chkKoumoku, Map<String, String> hissuKoumoku) {
    Map<String, Object> result = new HashMap<>();
    boolean errflg = true;
    String errMsgKoumoku = null;
    result.put("errflg", errflg);
    result.put("errMsgKoumoku", errMsgKoumoku);
    CheckCommon checkCommon = new CheckCommon();

    //画面項目のvalue値分検索、必須項目のkey値と一致した場合、
    //必須チェックを行いエラーだった場合、必須項目のValueをメッセージとして格納
    loop: for (String gamen : chkKoumoku.keySet()) {
      for (String hissu : hissuKoumoku.keySet())
        if (gamen == hissu) {
          errflg = checkCommon.checkBlankOrNULL((String) chkKoumoku.get(gamen));
          result.put("errflg", errflg);
          if (errflg == false) {
            errMsgKoumoku = hissuKoumoku.get(hissu);
            result.put("errMsgKoumoku", errMsgKoumoku);
            break loop;
          } else {
            break;
          }
        }
    }

    return result;
  }
}
