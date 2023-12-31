package model;

import java.io.Serializable;

public class SyainJouhouBean implements Serializable {

  private int id; //ユーザーID
  private String sei;
  private String sei_yomi;
  private String mei;
  private String mei_yomi;
  private String name; //名前 sei+mei
  private String nyuusyaYMD;
  private String taisytaYMD;
  private String seibetsu; //0=男　1=女 2=その他
  private String seinenngappi;
  private String age; //年齢　現在日付-生年月日 -> Yを抽出
  private String nenji; //年次 現在日付-入社年月日 -> YMMを抽出
  private String syusshin;
  private String juusyo;

  //コンスタラクター
  //一覧呼び出し（一般）
  public SyainJouhouBean(int id, String name, String nyuusyaYMD, String nenji,
      String syusshin, String juusyo) {
    this.id = id;
    this.name = name;
    this.nyuusyaYMD = nyuusyaYMD;
    this.nenji = nenji;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //一覧呼び出し（管理者）
  public SyainJouhouBean(int id, String name, String nyuusyaYMD, String nenji,
      String syusshin, String juusyo,String seibetsu, String age) {
    this.id = id;
    this.name = name;
    this.nyuusyaYMD = nyuusyaYMD;
    this.nenji = nenji;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
    this.seibetsu = seibetsu;
    this.age = age;
  }
  
  //必須チェック
  public SyainJouhouBean(int id,String sei, String sei_yomi, String mei, String mei_yomi, String nyuusyaYMD, String seibetsu,
      String seinenngappi, String syusshin, String juusyo) {
    this.id = id;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getAge() {
    return age;
  }

  public void setTanjoubi(String age) {
    this.age = age;
  }

  public String getNenji() {
    return nenji;
  }

  public void setNenji(String nenji) {
    this.nenji = nenji;
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
