package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

public class SkillEntity implements Serializable {

  private int userId; //ユーザーID
  private String userName; //ユーザー名
  private LocalDate nyuusyaYMD; //入社年月日
  private String sikaku1; //資格１
  private LocalDate sikaku1YMD; //資格１取得日時
  private String sikaku2; //資格２
  private LocalDate sikaku2YMD; //資格２取得日時
  private String sikaku3; //資格３
  private LocalDate sikaku3YMD; //資格３取得日時
  private LocalDate c1SYMD; //職歴１開始日時
  private LocalDate c1EYMD; //職歴１終了日時
  private String carrier1; //職歴１
  private String c1pos; //職歴１担当ポジション
  private String c1tech; //職歴１使用技術等
  private LocalDate c2SYMD; //職歴２開始日時
  private LocalDate c2EYMD; //職歴２終了日時
  private String carrier2; //職歴２
  private String c2pos; //職歴２担当ポジション
  private String c2tech; //職歴２使用技術等
  private LocalDate c3SYMD; //職歴３開始日時
  private LocalDate c3EYMD; //職歴３終了日時
  private String carrier3; //職歴３
  private String c3pos; //職歴３担当ポジション
  private String c3tech; //職歴３使用技術等
  private Timestamp timestamp;

  //コンストラクター
  public SkillEntity(int userId, String userName, LocalDate nyuusyaYMD, String skill1, LocalDate sikaku1ymd, String skill2, LocalDate sikaku2ymd,
      String skill3, LocalDate sikaku3ymd, LocalDate c1symd, LocalDate c1eymd, String carrier1, String c1pos,String c1tech,
      LocalDate c2symd, LocalDate c2eymd, String carrier2, String c2pos,String c2tech, LocalDate c3symd, LocalDate c3eymd,
      String carrier3, String c3pos,String c3tech) {
    this.userId = userId;
    this.userName = userName;
    this.nyuusyaYMD = nyuusyaYMD;
    this.sikaku1 = skill1;
    this.sikaku1YMD = sikaku1ymd;
    this.sikaku2 = skill2;
    this.sikaku2YMD = sikaku2ymd;
    this.sikaku3 = skill3;
    this.sikaku3YMD = sikaku3ymd;
    this.c1SYMD = c1symd;
    this.c1EYMD = c1eymd;
    this.carrier1 = carrier1;
    this.c1pos = c1pos;
    this.c1tech = c1tech;
    this.c2SYMD = c2symd;
    this.c2EYMD = c2eymd;
    this.carrier2 = carrier2;
    this.c2pos = c2pos;
    this.c2tech = c2tech;
    this.c3SYMD = c3symd;
    this.c3EYMD = c3eymd;
    this.carrier3 = carrier3;
    this.c3pos = c3pos;
    this.c3tech = c3tech;
  }
  
  //資格欄
  public SkillEntity(String sikaku1, LocalDate sikaku1ymd, String sikaku2, LocalDate sikaku2ymd, String sikaku3, LocalDate sikaku3ymd) {
    this.sikaku1 = sikaku1;
    sikaku1YMD = sikaku1ymd;
    this.sikaku2 = sikaku2;
    sikaku2YMD = sikaku2ymd;
    this.sikaku3 = sikaku3;
    sikaku3YMD = sikaku3ymd;
  }
  
  //職歴欄
  public SkillEntity(LocalDate c1symd, LocalDate c1eymd, String carrier1, String c1pos, String c1tech, LocalDate c2symd, LocalDate c2eymd,
      String carrier2, String c2pos, String c2tech, LocalDate c3symd, LocalDate c3eymd, String carrier3, String c3pos, String c3tech) {
    super();
    c1SYMD = c1symd;
    c1EYMD = c1eymd;
    this.carrier1 = carrier1;
    this.c1pos = c1pos;
    this.c1tech = c1tech;
    c2SYMD = c2symd;
    c2EYMD = c2eymd;
    this.carrier2 = carrier2;
    this.c2pos = c2pos;
    this.c2tech = c2tech;
    c3SYMD = c3symd;
    c3EYMD = c3eymd;
    this.carrier3 = carrier3;
    this.c3pos = c3pos;
    this.c3tech = c3tech;
  }

  //アクセサメソッド
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDate getNyuusyaYMD() {
    return nyuusyaYMD;
  }

  public void setNyuusyaYMD(LocalDate nyuusyaYMD) {
    this.nyuusyaYMD = nyuusyaYMD;
  }

  public String getSikaku1() {
    return sikaku1;
  }

  public void setSikaku1(String sikaku1) {
    this.sikaku1 = sikaku1;
  }

  public LocalDate getSikaku1YMD() {
    return sikaku1YMD;
  }

  public void setSikaku1YMD(LocalDate sikaku1ymd) {
    sikaku1YMD = sikaku1ymd;
  }

  public String getSikaku2() {
    return sikaku2;
  }

  public void setSikaku2(String sikaku2) {
    this.sikaku2 = sikaku2;
  }

  public LocalDate getSikaku2YMD() {
    return sikaku2YMD;
  }

  public void setSikaku2YMD(LocalDate sikaku2ymd) {
    sikaku2YMD = sikaku2ymd;
  }

  public String getSikaku3() {
    return sikaku3;
  }

  public void setSikaku3(String sikaku3) {
    this.sikaku3 = sikaku3;
  }

  public LocalDate getSikaku3YMD() {
    return sikaku3YMD;
  }

  public void setSikaku3YMD(LocalDate sikaku3ymd) {
    sikaku3YMD = sikaku3ymd;
  }

  public LocalDate getC1SYMD() {
    return c1SYMD;
  }

  public void setC1SYMD(LocalDate c1symd) {
    c1SYMD = c1symd;
  }

  public LocalDate getC1EYMD() {
    return c1EYMD;
  }

  public void setC1EYMD(LocalDate c1eymd) {
    c1EYMD = c1eymd;
  }

  public String getCarrier1() {
    return carrier1;
  }

  public void setCarrier1(String carrier1) {
    this.carrier1 = carrier1;
  }

  public String getC1pos() {
    return c1pos;
  }

  public void setC1pos(String c1pos) {
    this.c1pos = c1pos;
  }

  public String getC1tech() {
    return c1tech;
  }

  public void setC1tech(String c1tech) {
    this.c1tech = c1tech;
  }

  public LocalDate getC2SYMD() {
    return c2SYMD;
  }

  public void setC2SYMD(LocalDate c2symd) {
    c2SYMD = c2symd;
  }

  public LocalDate getC2EYMD() {
    return c2EYMD;
  }

  public void setC2EYMD(LocalDate c2eymd) {
    c2EYMD = c2eymd;
  }

  public String getCarrier2() {
    return carrier2;
  }

  public void setCarrier2(String carrier2) {
    this.carrier2 = carrier2;
  }

  public String getC2pos() {
    return c2pos;
  }

  public void setC2pos(String c2pos) {
    this.c2pos = c2pos;
  }

  public String getC2tech() {
    return c2tech;
  }

  public void setC2tech(String c2tech) {
    this.c2tech = c2tech;
  }

  public LocalDate getC3SYMD() {
    return c3SYMD;
  }

  public void setC3SYMD(LocalDate c3symd) {
    c3SYMD = c3symd;
  }

  public LocalDate getC3EYMD() {
    return c3EYMD;
  }

  public void setC3EYMD(LocalDate c3eymd) {
    c3EYMD = c3eymd;
  }

  public String getCarrier3() {
    return carrier3;
  }

  public void setCarrier3(String carrier3) {
    this.carrier3 = carrier3;
  }

  public String getC3pos() {
    return c3pos;
  }

  public void setC3pos(String c3pos) {
    this.c3pos = c3pos;
  }

  public String getC3tech() {
    return c3tech;
  }

  public void setC3tech(String c3tech) {
    this.c3tech = c3tech;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
