package model;

public class KoutusuuEntity {

  private int no;
  private int id;
  private String sendmailaddress;
  private String kukan1_start;
  private String kukan1_end;
  private String kingaku1;
  private String kukan2_start;
  private String kukan2_end;
  private String kingaku2;
  private String bikou;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSendmailaddress() {
    return sendmailaddress;
  }

  public void setSendmailaddress(String sendmailaddress) {
    this.sendmailaddress = sendmailaddress;
  }

  public String getKukan1_start() {
    return kukan1_start;
  }

  public void setKukan1_start(String kukan1_start) {
    this.kukan1_start = kukan1_start;
  }

  public String getKukan1_end() {
    return kukan1_end;
  }

  public void setKukan1_end(String kukan1_end) {
    this.kukan1_end = kukan1_end;
  }

  public String getKingaku1() {
    return kingaku1;
  }

  public void setKingaku1(String kingaku1) {
    this.kingaku1 = kingaku1;
  }

  public String getKukan2_start() {
    return kukan2_start;
  }

  public void setKukan2_start(String kukan2_start) {
    this.kukan2_start = kukan2_start;
  }

  public String getKukan2_end() {
    return kukan2_end;
  }

  public void setKukan2_end(String kukan2_end) {
    this.kukan2_end = kukan2_end;
  }

  public String getKingaku2() {
    return kingaku2;
  }

  public void setKingaku2(String kingaku2) {
    this.kingaku2 = kingaku2;
  }

  public String getBikou() {
    return bikou;
  }

  public void setBikou(String bikou) {
    this.bikou = bikou;
  }

}
