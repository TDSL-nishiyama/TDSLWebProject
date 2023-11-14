package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import model.MastaEntity;
import model.SyainJouhouEntity;

public class UserAddBL {
  
  public void userAdd(Map<String,String> input){
  
  int userid = 0;
  boolean kanriFlg = false;
  String loginId = "";
  String loginPassword = "";
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
  Date nyuusyaYMD = null;
  Date seinenngappi = null;
  
  //ID（）連番・仮登録用ID・仮登録パスワードの作成
  kanriFlg = Boolean.valueOf(input.get("kanriflg"));
  LoginDAO loginDAO = new LoginDAO();
  userid = Integer.parseInt(loginDAO.maxUserId()) + 1;   
  loginId = "kari" + String.valueOf(userid);
  loginPassword = "tdsl";
  //String→Dateの変換
  try {
    nyuusyaYMD = sdf.parse(input.get("nyuusyaYMD"));
    seinenngappi = sdf.parse(input.get("seinenngappi"));
  } catch (ParseException e) {
    e.printStackTrace();
  }
  
  //コンスタラクタでEntityに値を設定
  MastaEntity mastaEntity = new MastaEntity(userid, input.get("username"), kanriFlg,loginId, loginPassword);
  SyainJouhouEntity syainJouhouEntity = new SyainJouhouEntity(userid,
      input.get("sei"),input.get("mei"),input.get("seiyomi"),input.get("meiyomi")
      ,nyuusyaYMD,input.get("seibetsu"),seinenngappi,input.get("syusshin"),input.get("juusyo")
      );
  
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
