package model;

import java.io.Serializable;

public class KoutusuuBean extends KoutusuuEntity implements Serializable {
  private String userName;//koutsuuテーブルのID項目に基づくuserテーブルのname項目

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  
}
