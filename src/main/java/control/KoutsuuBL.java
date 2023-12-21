package control;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import constents.KoutsuuConst.KCommon;
import constents.KoutsuuConst.Koutsuu;
import constents.KoutsuuConst.KtimeStamp;
import constents.KoutsuuConst.SyuuseiG;
import constents.Table.User;
import control.common.CastCommon;
import model.KoutsuuBean;
import model.KoutsuuEntity;

public class KoutsuuBL {
  
  /**
   * {@index ステータス状態チェック}
   * @param beforeS　変更前のステータス
   * @param AfterS　変更後のステータス
   * @return　key:errorFlg true→エラーあり false→エラーなし
   * @return　key:errorMSG エラーメッセージ
   * @see 同一ステータスへの変更は不可。現状のステータスより前の状態への変更は不可（振込済→申請中など）
   */
  public Map<String, String> checkStatus(String beforeS, String AfterS) {
    Map<String, String> result = new HashMap<String, String>();
    result.put("errorFlg", "false");
    result.put("errorMSG", "");
    int intBefS = Integer.parseInt(chgStaToCode(beforeS));
    int intAftS = Integer.parseInt(AfterS);

    if (chgStaToCode(beforeS).equals(AfterS)) {
      result.put("errorFlg", "true");
      result.put("errorMSG", "同一ステータスへの変更はできません");
      return result;
    }

    if (intAftS < intBefS) {
      result.put("errorFlg", "true");
      StringBuilder sb = new StringBuilder();
      sb.append("ステータス：「");
      sb.append(beforeS);
      sb.append("」から「");
      sb.append(chgStatus(AfterS));
      sb.append("」への変更はできません");
      result.put("errorMSG", sb.toString());
    }
    return result;
  }

  /**
   * {交通費精算要求画面で入力した画面を新規でテーブルに挿入する}
   * @param gamenInfo
   * @throws SQLException
   */
  public void addKoutsuuYoukyu(List<KoutsuuBean> gamenInfo) throws SQLException {
    KoutsuuDAO dao = new KoutsuuDAO();

    //Noの取得（Koutsuuテーブルの最大値+1）
    int maxNo = dao.getMaxNo() + 1;

    //送信先メールアドレスの取得
    String sendMailAddress = "mail";

    //リスト数分の更新を実行（1レコードor2レコード想定）
    List<Map<String, Object>> statement = new ArrayList<Map<String, Object>>();
    Map<String, Object> m = new LinkedHashMap<String, Object>();
    for (int i = 0; i < gamenInfo.size(); i++) {
      //ステートメントの設定
      //koutsuu
      m.put(Koutsuu.COL_UNINO, maxNo);
      m.put(User.COL_USERID, gamenInfo.get(i).getId());
      m.put(Koutsuu.COL_SMAIL, sendMailAddress);
      m.put(Koutsuu.COL_RIYOUHIDUKE, gamenInfo.get(i).getRiyouhiduke());
      m.put(Koutsuu.COL_KUKAN_S, gamenInfo.get(i).getKukan_start());
      m.put(Koutsuu.COL_KUKAN_E, gamenInfo.get(i).getKukan_end());
      m.put(Koutsuu.COL_KINGAKU, gamenInfo.get(i).getKingaku());
      m.put(Koutsuu.COL_BIKOU, gamenInfo.get(i).getBikou());
      m.put(KtimeStamp.COL_YOUKYUU, LocalDateTime.now());
      m.put(KtimeStamp.COL_STATUS, "0");
      m.put(KtimeStamp.COL_TIMESTAMP, LocalDateTime.now());
      statement.add(m);

      //更新
      dao.InsertKoutsuuAndKtimestamp(statement);
      //no項目のカウントを進める
      maxNo++;
    }
  }

  /**
   * {@index 交通費精算確認画面に表示される情報の取得を行う}
   * @param selId　対象者の社員ID（ログインしているユーザーor選択したユーザー）
   * @param selNo　対象の申請No
   * @param selStatus 0=申請中 1=差戻中 2=承認済 3=振込済
   * @param selQuery requestされたQUERY_TYPE属性　0=where句あり（ID）　1=where句なし　2=where句あり（ステータス）
   * @return ArrayList<KotusuuBean>
   * @throws SQLException
   */
  public List<KoutsuuBean> getKoutsuuKakunin(int selId, int selNo, String selStatus, String selQuery)
      throws SQLException {
    List<KoutsuuBean> result = new ArrayList<KoutsuuBean>();
    List<KoutsuuEntity> resultDB = new ArrayList<KoutsuuEntity>();

    KoutsuuDAO dao = new KoutsuuDAO();
    resultDB = dao.selectKoutsuuKakunin(selId, selNo, selStatus, selQuery);

    CastCommon castCommon = new CastCommon();

    for (int i = 0; i < resultDB.size(); i++) {
      KoutsuuBean bean = new KoutsuuBean(resultDB.get(i).getNo(), resultDB.get(i).getId(),
          resultDB.get(i).getUserName(),
          resultDB.get(i).getSendmailaddress(), castCommon.chgLDTtoStr(resultDB.get(i).getRiyouhiduke()),
          resultDB.get(i).getKukan_start(), resultDB.get(i).getKukan_end(), resultDB.get(i).getKingaku(),
          resultDB.get(i).getBikou(), resultDB.get(i).getModoshiriyuu(),
          chgStatus(resultDB.get(i).getSeisannStatus()), castCommon.chgLDTtoStr(resultDB.get(i).getYoukyuuJikoku()),
          castCommon.chgLDTtoStr(resultDB.get(i).getYoukyuuJikoku()),
          castCommon.chgLDTtoStr(resultDB.get(i).getYoukyuuJikoku()));
      result.add(bean);
    }

    return result;
  }

  /**
   * {@index 承認・振込に設定する際の更新処理}
   * @param selNo
   * @param selStatus
   * @throws SQLException
   */
  public void updKoutsuuShounin(int selNo, String selStatus) throws SQLException {
    Map<String, Object> statement = new HashMap<>();
    //ステートメントの設定
    statement.put(KtimeStamp.COL_UNINO, selNo);
    if (selStatus.equals(KCommon.SYOUNIN)) {
      statement.put(KtimeStamp.COL_SYONIN, LocalDateTime.now());
    }
    statement.put(Koutsuu.COL_MODOSHIRIYUU, "");
    statement.put(KtimeStamp.COL_STATUS, selStatus);
    statement.put(KtimeStamp.COL_TIMESTAMP, LocalDateTime.now());

    //更新処理
    KoutsuuDAO dao = new KoutsuuDAO();
    dao.updateKoutsuuAndKtimestamp(statement);

  }

  /**
   * {@index 差戻ボタン押下時の更新処理}
   * @param selNo　選択したID
   * @param modoshiryuu　差戻理由
   * @throws SQLException
   */
  public void updKoutsuuSashimodoshi(int selNo, String modoshiryuu) throws SQLException {
    Map<String, Object> statement = new HashMap<>();
    //ステートメントの設定
    statement.put(Koutsuu.COL_MODOSHIRIYUU, modoshiryuu);
    statement.put(KtimeStamp.COL_UNINO, selNo);
    statement.put(KtimeStamp.COL_SASHIMODOSHI, LocalDateTime.now());
    statement.put(KtimeStamp.COL_STATUS, KCommon.SASHIMODOSHI);
    statement.put(KtimeStamp.COL_TIMESTAMP, LocalDateTime.now());
    //更新処理
    KoutsuuDAO dao = new KoutsuuDAO();
    dao.updateKoutsuuSashimodoshi(statement);
  }

  /**
   * {@index 修正ボタン押下時の更新処理}
   * @param gamenInfo　画面情報
   * @throws SQLException
   */
  public void updKoutsuuSyuusei(Map<String, Object> gamenInfo) throws SQLException {
    Map<String, Object> statement = new HashMap<>();
    statement = gamenInfo;
    statement.put(Koutsuu.COL_KUKAN_S, gamenInfo.get(SyuuseiG.GAMEN_KUKAN_S));//画面名とカラム名が違うので
    statement.put(Koutsuu.COL_KUKAN_E, gamenInfo.get(SyuuseiG.GAMEN_KUKAN_E));//画面名とカラム名が違うので
    statement.put(KtimeStamp.COL_TIMESTAMP, LocalDateTime.now());

    //更新処理
    KoutsuuDAO dao = new KoutsuuDAO();
    dao.updateKoutsuuSyuusei(statement);
  }

  /**
   * {@index ステータスコード変換用}
   * @param status 0,1,2,3
   * @return 変換結果　0→申請中 1→差戻中 2→承認済 3→振込済
   */
  private String chgStatus(String status) {
    String result = null;

    switch (status) {
    case KCommon.SHINSEI:
      result = "申請中";
      break;
    case KCommon.SASHIMODOSHI:
      result = "差戻中";
      break;
    case KCommon.SYOUNIN:
      result = "承認済";
      break;
    case KCommon.FURIKOMIZUMI:
      result = "振込済";
      break;
    }

    return result;
  }

  /**
   * {@index ステータスコード変換用}
   * @param status 申請中,差戻中,承認済,振込済
   * @return 変換結果　0←申請中 1←差戻中 2←承認済 3←振込済
   */
  private String chgStaToCode(String status) {
    String result = null;

    switch (status) {
    case "申請中":
      result = KCommon.SHINSEI;
      break;
    case "差戻中":
      result = KCommon.SASHIMODOSHI;
      break;
    case "承認済":
      result = KCommon.SYOUNIN;
      break;
    case "振込済":
      result = KCommon.FURIKOMIZUMI;
      break;
    }

    return result;
  }
}
