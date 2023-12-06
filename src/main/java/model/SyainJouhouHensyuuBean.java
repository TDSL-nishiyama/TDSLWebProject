package model;

import java.io.Serializable;

public class SyainJouhouHensyuuBean implements Serializable {

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
  //呼び出し（一般）
  public SyainJouhouHensyuuBean(int id, String sei, String sei_yomi, String mei, String mei_yomi, String name,
      String nyuusyaYMD, String nenji, String syusshin, String juusyo) {
    this.id = id;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.name = name;
    this.nyuusyaYMD = nyuusyaYMD;
    this.nenji = nenji;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //呼び出し（管理者・全項目）
  public SyainJouhouHensyuuBean(int id, String sei, String sei_yomi, String mei, String mei_yomi, String name,
      String nyuusyaYMD, String taisytaYMD, String seibetsu, String seinenngappi, String age, String nenji,
      String syusshin, String juusyo) {
    super();
    this.id = id;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.name = name;
    this.nyuusyaYMD = nyuusyaYMD;
    this.taisytaYMD = taisytaYMD;
    this.seibetsu = seibetsu;
    this.seinenngappi = seinenngappi;
    this.age = age;
    this.nenji = nenji;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //更新（一般）
  public SyainJouhouHensyuuBean(int id, String sei, String sei_yomi, String mei, String mei_yomi, String nyuusyaYMD,
      String syusshin, String juusyo) {
    this.id = id;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //更新（管理者）
  public SyainJouhouHensyuuBean(int id, String name, String sei, String sei_yomi, String mei, String mei_yomi,
      String nyuusyaYMD,
      String seibetsu, String seinenngappi, String syusshin, String juusyo) {
    this.id = id;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.name = name;
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
