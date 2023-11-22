package model;

import java.io.Serializable;
import java.util.Date;

public class MastaBean extends SyainJouhouBean implements Serializable {

  private int userid;
  private String username;
  private boolean kanriFlg;
  private String sakujo; //(1=退職)
  private String loginid;
  private String loginpassword;

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

  @Override
  public String getName() {

    return super.getName();
  }

  @Override
  public void setName(String name) {

    super.setName(name);
  }

  @Override
  public String getSei() {

    return super.getSei();
  }

  @Override
  public void setSei(String sei) {

    super.setSei(sei);
  }

  @Override
  public String getSei_yomi() {

    return super.getSei_yomi();
  }

  @Override
  public void setSei_yomi(String sei_yomi) {

    super.setSei_yomi(sei_yomi);
  }

  @Override
  public String getMei() {

    return super.getMei();
  }

  @Override
  public void setMei(String mei) {

    super.setMei(mei);
  }

  @Override
  public String getMei_yomi() {

    return super.getMei_yomi();
  }

  @Override
  public void setMei_yomi(String mei_yomi) {

    super.setMei_yomi(mei_yomi);
  }

  @Override
  public Date getNyuusyaYMD() {

    return super.getNyuusyaYMD();
  }

  @Override
  public void setNyuusyaYMD(Date nyuusyaYMD) {

    super.setNyuusyaYMD(nyuusyaYMD);
  }

  @Override
  public Date getTaisytaYMD() {

    return super.getTaisytaYMD();
  }

  @Override
  public void setTaisytaYMD(Date taisytaYMD) {

    super.setTaisytaYMD(taisytaYMD);
  }

  @Override
  public String getSeibetsu() {

    return super.getSeibetsu();
  }

  @Override
  public void setSeibetsu(String seibetsu) {

    super.setSeibetsu(seibetsu);
  }

  @Override
  public Date getSeinenngappi() {

    return super.getSeinenngappi();
  }

  @Override
  public void setSeinenngappi(Date seinenngappi) {

    super.setSeinenngappi(seinenngappi);
  }

  @Override
  public String getAge() {

    return super.getAge();
  }

  @Override
  public void setTanjoubi(String age) {

    super.setTanjoubi(age);
  }

  @Override
  public String getNenji() {

    return super.getNenji();
  }

  @Override
  public void setNenji(String nenji) {

    super.setNenji(nenji);
  }

  @Override
  public String getSyusshin() {

    return super.getSyusshin();
  }

  @Override
  public void setSyusshin(String syusshin) {

    super.setSyusshin(syusshin);
  }

  @Override
  public String getJuusyo() {

    return super.getJuusyo();
  }

  @Override
  public void setJuusyo(String juusyo) {

    super.setJuusyo(juusyo);
  }

}
