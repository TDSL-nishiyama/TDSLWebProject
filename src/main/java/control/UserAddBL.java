package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import model.MastaEntity;
import model.SyainJouhouEntity;

public class UserAddBL {

  /**
   *{@index必須項目確認} 
   * @param chkKoumoku チェックしたい項目名（対象画面情報のvalue）
   * @return
   */
  public boolean userAddCheck(String chkKoumoku) {

    boolean result = true;

    if (chkKoumoku.trim() == "") {
      result = false;
    }
    return result;
  }

  public void userAdd(Map<String, String> addKoumoku) {

    int userid = 0;
    boolean kanriFlg = false;
    String loginId = "";
    String loginPassword = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date nyuusyaYMD = null;
    Date seinenngappi = null;

    //ID（）連番・仮登録用ID・仮登録パスワードの作成
    kanriFlg = Boolean.valueOf(addKoumoku.get("kanriflg"));
    LoginDAO loginDAO = new LoginDAO();
    userid = loginDAO.maxUserId() + 1;
    loginId = "kari" + String.valueOf(userid);
    loginPassword = "tdsl";
    //String→Dateの変換
    try {
      nyuusyaYMD = sdf.parse(addKoumoku.get("nyuusyaYMD"));
      seinenngappi = sdf.parse(addKoumoku.get("seinenngappi"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    //コンスタラクタでEntityに値を設定
    MastaEntity mastaEntity = new MastaEntity(userid, addKoumoku.get("username"), kanriFlg, loginId, loginPassword);
    SyainJouhouEntity syainJouhouEntity = new SyainJouhouEntity(userid,
        addKoumoku.get("sei"), addKoumoku.get("seiyomi"), addKoumoku.get("mei"), addKoumoku.get("meiyomi"), nyuusyaYMD,
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
