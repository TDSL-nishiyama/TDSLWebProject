package model;

import java.io.Serializable;

public class LoginEntity implements Serializable {
  
  private String userId;         //ユーザーID
  private String loginId;    //ログインID
  private String loginPassword;   //ログインパスワード
  
  //コンストラクタ（loginId）
  public LoginEntity(String loginId){
    this.loginId = loginId;
  }
  
  public LoginEntity(String loginId,String loginPassword){
    this.loginId = loginId;
    this.loginPassword = loginPassword;
  }
  
  //アクセサメソッド
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginPassword() {
    return loginPassword;
  }

  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }

}
