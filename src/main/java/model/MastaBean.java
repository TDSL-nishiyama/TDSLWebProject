package model;

import java.io.Serializable;
import java.util.Date;

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
  private Date nyuusyaYMD;
  private Date taisytaYMD;
  private String seibetsu; //0=男　1=女 2=その他
  private Date seinenngappi;
  private String syusshin;
  private String juusyo;
  
  //コンストラクター
  public MastaBean(int userid, String username, boolean kanriFlg, String loginid, String loginpassword,
      String sei, String sei_yomi, String mei, String mei_yomi, Date nyuusyaYMD, Date taisytaYMD, String seibetsu,
      Date seinenngappi, String syusshin, String juusyo) {
    super();
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

  public Date getNyuusyaYMD() {
    return nyuusyaYMD;
  }

  public void setNyuusyaYMD(Date nyuusyaYMD) {
    this.nyuusyaYMD = nyuusyaYMD;
  }

  public Date getTaisytaYMD() {
    return taisytaYMD;
  }

  public void setTaisytaYMD(Date taisytaYMD) {
    this.taisytaYMD = taisytaYMD;
  }

  public String getSeibetsu() {
    return seibetsu;
  }

  public void setSeibetsu(String seibetsu) {
    this.seibetsu = seibetsu;
  }

  public Date getSeinenngappi() {
    return seinenngappi;
  }

  public void setSeinenngappi(Date seinenngappi) {
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


}
