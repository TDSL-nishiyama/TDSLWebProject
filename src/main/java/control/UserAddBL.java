package control;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import control.common.CastCommon;
import model.MastaEntity;
import model.SyainJouhouEntity;

public class UserAddBL {
  
  /**
   * {@index ユーザー追加実行処理} 
   * @param addKoumoku
   * @throws SQLException 
   * @throws ParseException 
   */
  public void addUser(Map<String, Object> addKoumoku) throws SQLException, ParseException {

    int userid = 0;
    boolean kanriFlg = false;
    String loginId = "";
    String loginPassword = "";
    Date nyuusyaYMD = null;
    Date seinenngappi = null;

    //ID（）連番・仮登録用ID・仮登録パスワードの作成
    kanriFlg = (boolean) addKoumoku.get("kanriflg");
    LoginDAO loginDAO = new LoginDAO();
    userid = loginDAO.maxUserId() + 1;
    loginId = "kari" + String.valueOf(userid);
    loginPassword = "tdsl";
    //String→Dateの変換(この時点での日付はチェックを通っているためParseExceptionは発生しない想定)
    CastCommon castCommon = new CastCommon();
    nyuusyaYMD = castCommon.chgStrToDate(castCommon.chgGamenDateToStr(addKoumoku.get("nyuusyaYMD").toString()));
    seinenngappi = castCommon.chgStrToDate(castCommon.chgGamenDateToStr(addKoumoku.get("seinenngappi").toString()));

    //コンスタラクタでEntityに値を設定
    MastaEntity mastaEntity = new MastaEntity(userid, addKoumoku.get("username").toString(), kanriFlg, loginId, loginPassword);
    SyainJouhouEntity syainJouhouEntity = new SyainJouhouEntity(userid,
        addKoumoku.get("sei").toString(), addKoumoku.get("sei_yomi").toString(), addKoumoku.get("mei").toString(), addKoumoku.get("mei_yomi").toString(),
        nyuusyaYMD,
        addKoumoku.get("seibetsu").toString(), seinenngappi, addKoumoku.get("syusshin").toString(), addKoumoku.get("juusyo").toString());

    //各種テーブルへの登録
    MastaDAOInsertUpdate useraddDAOInsUp = new MastaDAOInsertUpdate();
    useraddDAOInsUp.InsertUserAdd(mastaEntity, syainJouhouEntity);
  }
}
