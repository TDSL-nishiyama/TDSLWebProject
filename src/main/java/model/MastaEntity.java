package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MastaEntity implements Serializable {

  private int userid;
  private String username;
  private boolean kanriFlg;
  private String sakujo; //(1=退職)
  private String loginid;
  private String loginpassword;
  private String mailAddress;
  private Timestamp timestamp;
  
  //コンストラクタ
  public MastaEntity(int userid, String username, boolean kanriFlg, String loginid,String loginpassword) {
    this.userid = userid;
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.loginid = loginid;
    this.loginpassword = loginpassword;
  }
  
  public MastaEntity(int userid, String username, boolean kanriFlg, String loginid,String loginpassword,String sakujo) {
    this.userid = userid;
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.loginid = loginid;
    this.loginpassword = loginpassword;
    this.sakujo = sakujo;
  }
  
  //ユーザー一覧
  public MastaEntity(int userid, String username, boolean kanriFlg, String loginid) {
    this.userid = userid;
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.loginid = loginid;
  }

  public MastaEntity(int userid) {
    this.userid = userid;
  }


  //アクセサメソッド
  public int getUserid() {
    return userid;
  }

  public void setUseridU(int useridU) {
    this.userid = useridU;
  }

  public String getUserName() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public boolean getKanriFlg() {
    return kanriFlg;
  }

  public void setKanriFlg(boolean kanriFlg) {
    this.kanriFlg = kanriFlg;
  }

  public String getSakujo() {
    return sakujo;
  }

  public void setSakujo(String sakujo) {
    this.sakujo = sakujo;
  }

  public String getLoginid() {
    return loginid;
  }

  public void setLoginid(String loginid) {
    this.loginid = loginid;
  }

  public String getLoginpassword() {
    return loginpassword;
  }

  public void setLoginpassword(String loginpassword) {
    this.loginpassword = loginpassword;
  }

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
