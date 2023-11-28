package control;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import control.common.CastCommon;
import control.common.CheckCommon;
import model.MastaEntity;
import model.SyainJouhouEntity;

public class UserAddBL {

  /**
   *{@index 必須項目確認} 
   * @param chkKoumoku チェックしたい項目名（対象画面情報のvalue）
   * @return key:errflg エラーの可否 true = 正常　false = 異常
   * @return key:errMsgKoumoku hissuKoumokuの中でエラーとなった項目
   */
  public Map<String,Object> checkHissu(Map<String,String> gamenInfo,Map<String,String> hissuKoumoku) {
    Map<String,Object> result = new HashMap<>();
    boolean errflg = true;
    String errMsgKoumoku = null;
    result.put("errflg", errflg);
    result.put("errMsgKoumoku", errMsgKoumoku);
    CheckCommon checkCommon = new CheckCommon();
    
    //画面項目のvalue値分検索、必須項目のkey値と一致した場合、
    //必須チェックを行いエラーだった場合、必須項目のValueをメッセージとして格納
    loop: for (String gamen : gamenInfo.keySet()) {
      for (String hissu : hissuKoumoku.keySet())
        if (gamen == hissu) {
          errflg = checkCommon.checkBlankOrNULL(gamenInfo.get(gamen));
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

  /**
   * {@index ユーザー追加実行処理} 
   * @param addKoumoku
   */
  public void addUser(Map<String, String> addKoumoku) {

    int userid = 0;
    boolean kanriFlg = false;
    String loginId = "";
    String loginPassword = "";
    Date nyuusyaYMD = null;
    Date seinenngappi = null;

    //ID（）連番・仮登録用ID・仮登録パスワードの作成
    kanriFlg = Boolean.valueOf(addKoumoku.get("kanriflg"));
    LoginDAO loginDAO = new LoginDAO();
    userid = loginDAO.maxUserId() + 1;
    loginId = "kari" + String.valueOf(userid);
    loginPassword = "tdsl";
    //String→Dateの変換
    CastCommon castCommon = new CastCommon();
    nyuusyaYMD = castCommon.chgStrToDate(addKoumoku.get("nyuusyaYMD"));
    seinenngappi = castCommon.chgStrToDate(addKoumoku.get("seinenngappi"));

    //コンスタラクタでEntityに値を設定
    MastaEntity mastaEntity = new MastaEntity(userid, addKoumoku.get("username"), kanriFlg, loginId, loginPassword);
    SyainJouhouEntity syainJouhouEntity = new SyainJouhouEntity(userid,
        addKoumoku.get("sei"), addKoumoku.get("sei_yomi"), addKoumoku.get("mei"), addKoumoku.get("mei_yomi"), nyuusyaYMD,
        addKoumoku.get("seibetsu"), seinenngappi, addKoumoku.get("syusshin"), addKoumoku.get("juusyo"));

    //各種テーブルへの登録
    MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate();

    //TODO　トランザクションの開始終了処理

    //ユーザーテーブル登録
    useraddDAOInsUp.InsertUser(mastaEntity);

    //ログインテーブル仮登録
    useraddDAOInsUp.InsertLogin(mastaEntity);

    //ユーザー詳細テーブル仮登録
    useraddDAOInsUp.InsertUserShoisai(syainJouhouEntity);

    //エラー項目が一つでもある場合、システムエラー

    //トランザクションの終了
  }
}
