package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class KoutsuuEntity implements Serializable{

  private int no;
  private int id;
  private String userName;
  private String sendmailaddress;
  private LocalDateTime riyouhiduke;
  private String kukan_start;
  private String kukan_end;
  private String kingaku;
  private String bikou;
  private String modoshiriyuu;
  private LocalDateTime youkyuuJikoku;
  private LocalDateTime sashimodoshiJikoku;
  private LocalDateTime syouninJikoku;
  private String seisannStatus;
  private Timestamp timestamp;
  
  //交通費精算(確認/承認)画面を開いた際の処理
  public KoutsuuEntity(int no, int id, String userName,String sendmailaddress, LocalDateTime riyouhiduke, String kukan_start, String kukan_end,
      String kingaku, String bikou, String modoshiriyuu, String  seisannStatus,LocalDateTime youkyuuJikoku,LocalDateTime sashimodoshiJikoku,LocalDateTime syouninJikoku) {
    this.no = no;
    this.id = id;
    this.userName = userName;
    this.sendmailaddress = sendmailaddress;
    this.riyouhiduke = riyouhiduke;
    this.kukan_start = kukan_start;
    this.kukan_end = kukan_end;
    this.kingaku = kingaku;
    this.bikou = bikou;
    this.modoshiriyuu = modoshiriyuu;
    this.youkyuuJikoku = youkyuuJikoku;
    this.sashimodoshiJikoku = sashimodoshiJikoku;
    this.syouninJikoku = syouninJikoku;
    this.seisannStatus = seisannStatus;
  }
  
  public KoutsuuEntity() {
  }

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
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getSendmailaddress() {
    return sendmailaddress;
  }
  public void setSendmailaddress(String sendmailaddress) {
    this.sendmailaddress = sendmailaddress;
  }
  public String getKukan_start() {
    return kukan_start;
  }
  public void setKukan_start(String kukan_start) {
    this.kukan_start = kukan_start;
  }
  public String getKukan_end() {
    return kukan_end;
  }
  public void setKukan_end(String kukan_end) {
    this.kukan_end = kukan_end;
  }
  public String getKingaku() {
    return kingaku;
  }
  public void setKingaku(String kingaku) {
    this.kingaku = kingaku;
  }
  public String getBikou() {
    return bikou;
  }
  public void setBikou(String bikou) {
    this.bikou = bikou;
  }
  public LocalDateTime getYoukyuuJikoku() {
    return youkyuuJikoku;
  }
  public void setYoukyuuJikoku(LocalDateTime youkyuuJikoku) {
    this.youkyuuJikoku = youkyuuJikoku;
  }
  public LocalDateTime getSashimodoshiJikoku() {
    return sashimodoshiJikoku;
  }
  public void setSashimodoshiJikoku(LocalDateTime sashimodoshiJikoku) {
    this.sashimodoshiJikoku = sashimodoshiJikoku;
  }
  public LocalDateTime getSyouninJikoku() {
    return syouninJikoku;
  }
  public void setSyouninJikoku(LocalDateTime syouninJikoku) {
    this.syouninJikoku = syouninJikoku;
  }
  public String getSeisannStatus() {
    return seisannStatus;
  }
  public void setSeisannStatus(String seisannStatus) {
    this.seisannStatus = seisannStatus;
  }
  public Timestamp getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public LocalDateTime getRiyouhiduke() {
    return riyouhiduke;
  }

  public void setRiyouhiduke(LocalDateTime riyouhiduke) {
    this.riyouhiduke = riyouhiduke;
  }

  public String getModoshiriyuu() {
    return modoshiriyuu;
  }

  public void setModoshiriyuu(String modoshiriyuu) {
    this.modoshiriyuu = modoshiriyuu;
  }
  
}