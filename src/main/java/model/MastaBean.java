package model;

import java.io.Serializable;

public class MastaBean implements Serializable {
  
  private int userid;
  private String username;
  private boolean kanriFlg;
  private String sakujo; //(1=退職)
  private String loginid;
  private String loginpassword;
  private String sei;
  private String sei_yomi;
  private String mei;
  private String mei_yomi;
  private String nyuusyaYMD;
  private String taisytaYMD;
  private String seibetsu; //0=男　1=女 2=その他
  private String seinenngappi;
  private String syusshin;
  private String juusyo;
  private String mailAddress;
  
  //コンストラクター
  //ResultUserUpdActionの画面項目保持用
  public MastaBean(int userid, String username, boolean kanriFlg, String loginid, String loginpassword,
      String sei, String sei_yomi, String mei, String mei_yomi, String nyuusyaYMD, String taisytaYMD, String seibetsu,
      String seinenngappi, String syusshin, String juusyo) {
    this.userid = userid;
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.loginid = loginid;
    this.loginpassword = loginpassword;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.nyuusyaYMD = nyuusyaYMD;
    this.taisytaYMD = taisytaYMD;
    this.seibetsu = seibetsu;
    this.seinenngappi = seinenngappi;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }
  
  //ResultUserAddActionのエラー時の画面項目保持用
  public MastaBean(String username, boolean kanriFlg, String sei, String sei_yomi, String mei, String mei_yomi,
      String nyuusyaYMD, String seibetsu, String seinenngappi, String syusshin, String juusyo) {
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.nyuusyaYMD = nyuusyaYMD;
    this.seibetsu = seibetsu;
    this.seinenngappi = seinenngappi;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }
  
  //ResultUserUpdActionのエラー時の画面項目保持用
  public MastaBean(int userid,String username, boolean kanriFlg, String sei, String sei_yomi, String mei, String mei_yomi,
      String nyuusyaYMD, String seibetsu, String seinenngappi, String syusshin, String juusyo) {
    this.userid = userid;
    this.username = username;
    this.kanriFlg = kanriFlg;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.nyuusyaYMD = nyuusyaYMD;
    this.seibetsu = seibetsu;
    this.seinenngappi = seinenngappi;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //メールアドレスマスタ用
  public MastaBean(int userid,String userName ,String mailAddress) {
    this.userid = userid;
    this.username = userName;
    this.mailAddress = mailAddress;
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

  public String getSei() {
    return sei;
  }

  public void setSei(String sei) {
    this.sei = sei;
  }

  public String getSei_yomi() {
    return sei_yomi;
  }

  public void setSei_yomi(String sei_yomi) {
    this.sei_yomi = sei_yomi;
  }

  public String getMei() {
    return mei;
  }

  public void setMei(String mei) {
    this.mei = mei;
  }

  public String getMei_yomi() {
    return mei_yomi;
  }

  public void setMei_yomi(String mei_yomi) {
    this.mei_yomi = mei_yomi;
  }

  public String getNyuusyaYMD() {
    return nyuusyaYMD;
  }

  public void setNyuusyaYMD(String nyuusyaYMD) {
    this.nyuusyaYMD = nyuusyaYMD;
  }

  public String getTaisytaYMD() {
    return taisytaYMD;
  }

  public void setTaisytaYMD(String taisytaYMD) {
    this.taisytaYMD = taisytaYMD;
  }

  public String getSeibetsu() {
    return seibetsu;
  }

  public void setSeibetsu(String seibetsu) {
    this.seibetsu = seibetsu;
  }

  public String getSeinenngappi() {
    return seinenngappi;
  }

  public void setSeinenngappi(String seinenngappi) {
    this.seinenngappi = seinenngappi;
  }

  public String getSyusshin() {
    return syusshin;
  }

  public void setSyusshin(String syusshin) {
    this.syusshin = syusshin;
  }

  public String getJuusyo() {
    return juusyo;
  }

  public void setJuusyo(String juusyo) {
    this.juusyo = juusyo;
  }

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
  }


}
