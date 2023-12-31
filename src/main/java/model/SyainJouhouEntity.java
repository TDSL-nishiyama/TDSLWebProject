package model;

import java.io.Serializable;
import java.util.Date;

public class SyainJouhouEntity implements Serializable {

  private int id; //ユーザーID
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

  //一般ユーザー
  public SyainJouhouEntity(int id, String sei, String sei_yomi, String mei, String mei_yomi, Date nyuusyaYMD, String syusshin,String juusyo) {
    this.id = id;
    this.sei = sei;
    this.sei_yomi = sei_yomi;
    this.mei = mei;
    this.mei_yomi = mei_yomi;
    this.nyuusyaYMD = nyuusyaYMD;
    this.syusshin = syusshin;
    this.juusyo = juusyo;
  }

  //初回登録
  public SyainJouhouEntity(int id,String sei, String sei_yomi, String mei, String mei_yomi, Date nyuusyaYMD,
      String seibetsu, Date seinenngappi, String syusshin, String juusyo) {
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
  
  //DB項目すべて
  public SyainJouhouEntity(int id, String sei, String sei_yomi, String mei, String mei_yomi, Date nyuusyaYMD,
      Date taisytaYMD, String seibetsu, Date seinenngappi, String syusshin, String juusyo) {
    this.id = id;
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

  public SyainJouhouEntity(int id, String sei, String mei) {
    this.id = id;
    this.sei = sei;
    this.mei = mei;
  }

  public SyainJouhouEntity() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
