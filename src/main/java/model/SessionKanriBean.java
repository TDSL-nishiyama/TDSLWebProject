package model;

import java.io.Serializable;

public class SessionKanriBean implements Serializable {
  
  private int userId; //ユーザーID 
    private String loginId; //ログインID
    private String loginName; //ログインユーザー名
    private boolean kanriFlg; //管理フラグ("TRUE"=管理者権限あり)

    //コンストラクタ
    public SessionKanriBean() {

    }
    //コンストラクタ（loginId）
    public SessionKanriBean(String loginId) {
        this.loginId = loginId;
    }

    //コンストラクタ（id,password,名前,管理者フラグ）
    public SessionKanriBean(int userId, String loginId,String loginName,boolean kanriFlg) {
        this.userId = userId;
        this.loginId = loginId;
        this.loginName = loginName;
        this.kanriFlg = kanriFlg;
    }
    
    //アクセサメソッド
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public boolean getKanriFlg() {
    return kanriFlg;
  }

  public void setKanriFlg(boolean kanriFlg) {
    this.kanriFlg = kanriFlg;
  }
}

