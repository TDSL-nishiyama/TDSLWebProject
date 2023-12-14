package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class KoutsuuBean implements Serializable {

  private int no;
  private int id;
  private String userName;//koutsuuテーブルのID項目に基づくuserテーブルのname項目
  private String sendmailaddress;
  private LocalDateTime riyouhiduke;
  private String riyouView;//利用日付の表示用項目
  private String kukan_start;
  private String kukan_end;
  private String kingaku;
  private String bikou;
  private String modoshiriyuu;//差戻理由
  private LocalDateTime youkyuuJikoku;
  private String youkyuuView;//申請日付（要求時刻）の表示用項目
  private LocalDateTime sashimodoshiJikoku;
  private String sashimodoshiView;//差戻日付（差戻時刻）の表示用項目
  private String sashimodoshiRiyuu;
  private LocalDateTime syouninJikoku;
  private String syouninView;//承認日付（承認時刻）の表示用項目
  private String seisannStatus;
  private Timestamp timestamp;
  
  //交通費精算要求
  public KoutsuuBean(int id,LocalDateTime riyouhiduke, String kukan_start, String kukan_end, String kingaku,
      String bikou) {
    this.id = id;
    this.riyouhiduke = riyouhiduke;
    this.kukan_start = kukan_start;
    this.kukan_end = kukan_end;
    this.kingaku = kingaku;
    this.bikou = bikou;
  }
  
  //交通費精算確認・承認
  public KoutsuuBean(int no, int id, String userName,String sendmailaddress,String riyouView, String kukan_start, String kukan_end, String kingaku,
      String bikou,String modoshiriyuu,String seisannStatus,String youkyuuView,String sashimodoshiView,String syouninView) {
    this.no = no;
    this.id = id;
    this.userName = userName;
    this.riyouView = riyouView;
    this.sendmailaddress = sendmailaddress;
    this.kukan_start = kukan_start;
    this.kukan_end = kukan_end;
    this.kingaku = kingaku;
    this.bikou = bikou;
    this.modoshiriyuu = modoshiriyuu;
    this.seisannStatus = seisannStatus;
    this.youkyuuView = youkyuuView;
    this.sashimodoshiView = sashimodoshiView;
    this.syouninView = syouninView;
  }
  
  //交通費差戻
  public KoutsuuBean(int no,String modoshiriyuu) {
    this.no = no;
    this.modoshiriyuu = modoshiriyuu;
  }
  
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public String getYoukyuuView() {
    return youkyuuView;
  }

  public void setYoukyuuView(String youkyuuView) {
    this.youkyuuView = youkyuuView;
  }

  public LocalDateTime getRiyouhiduke() {
    return riyouhiduke;
  }

  public void setRiyouhiduke(LocalDateTime riyouhiduke) {
    this.riyouhiduke = riyouhiduke;
  }

  public String getRiyouView() {
    return riyouView;
  }

  public void setRiyouView(String riyouView) {
    this.riyouView = riyouView;
  }

  public String getSashimodoshiRiyuu() {
    return sashimodoshiRiyuu;
  }

  public void setSashimodoshiRiyuu(String sashimodoshiRiyuu) {
    this.sashimodoshiRiyuu = sashimodoshiRiyuu;
  }

  public String getSashimodoshiView() {
    return sashimodoshiView;
  }

  public void setSashimodoshiView(String sashimodoshiView) {
    this.sashimodoshiView = sashimodoshiView;
  }

  public String getSyouninView() {
    return syouninView;
  }

  public void setSyouninView(String syouninView) {
    this.syouninView = syouninView;
  }

  public String getModoshiriyuu() {
    return modoshiriyuu;
  }

  public void setModoshiriyuu(String modoshiriyuu) {
    this.modoshiriyuu = modoshiriyuu;
  }
  
}
