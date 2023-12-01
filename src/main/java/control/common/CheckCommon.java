package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckCommon {

  public boolean checkDate(String pDate) {
    boolean result = true;

    if (pDate == null || pDate.equals("")) {
      return result;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      sdf.setLenient(false);
      sdf.parse(pDate);
    } catch (ParseException e) {
      result = false;
    }
    return result;
  }

  public boolean checkBlankOrNULL(String str) {
    boolean result = true;

    if (str == null || str.trim().equals("")) {
      result = false;
    }

    return result;
  }

  public int checkUserId(int userId) {

    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(userId);
    DAOCommon dao = new DAOCommon();
    result = dao.countSQL("checkUserId.sql", statement);

    return result;
  }

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
