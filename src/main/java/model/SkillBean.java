package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SkillBean implements Serializable {

  private int userId; //ユーザーID
  private String userName;
  private LocalDateTime nyuusyaYMD;
  private String nyuusyaYMDView;
  private String sikaku1; //資格１
  private LocalDateTime sikaku1YMD; //資格１取得日時
  private String sikaku2; //資格２
  private LocalDateTime sikaku2YMD; //資格２取得日時
  private String sikaku3; //資格３
  private LocalDateTime sikaku3YMD; //資格３取得日時
  private LocalDateTime c1SYMD; //職歴１開始日時
  private LocalDateTime c1EYMD; //職歴１終了日時
  private String c1kikanView; //職歴１従事期間
  private String carrier1; //職歴１
  private String c1pos; //職歴１担当ポジション
  private LocalDateTime c2SYMD; //職歴２開始日時
  private LocalDateTime c2EYMD; //職歴２終了日時
  private String c2kikanView; //職歴２従事期間
  private String carrier2; //職歴２
  private String c2pos; //職歴２担当ポジション
  private LocalDateTime c3SYMD; //職歴３開始日時
  private LocalDateTime c3EYMD; //職歴３終了日時
  private String c3kikanView; //職歴３従事期間
  private String carrier3; //職歴３
  private String c3pos; //職歴３担当ポジション
  
  //コンストラクター
  public SkillBean(int userId,String userName,String nyuusyaYMDView, String sikaku1, LocalDateTime sikaku1ymd, String sikaku2, LocalDateTime sikaku2ymd,
      String sikaku3, LocalDateTime sikaku3ymd, LocalDateTime c1symd, LocalDateTime c1eymd, String c1kikanView,
      String carrier1, String c1pos, LocalDateTime c2symd, LocalDateTime c2eymd, String c2kikanView, String carrier2,
      String c2pos, LocalDateTime c3symd, LocalDateTime c3eymd, String c3kikanView, String carrier3, String c3pos) {
    this.userId = userId;
    this.sikaku1 = sikaku1;
    this.sikaku1YMD = sikaku1ymd;
    this.sikaku2 = sikaku2;
    this.sikaku2YMD = sikaku2ymd;
    this.sikaku3 = sikaku3;
    this.sikaku3YMD = sikaku3ymd;
    this.c1SYMD = c1symd;
    this.c1EYMD = c1eymd;
    this.c1kikanView = c1kikanView;
    this.carrier1 = carrier1;
    this.c1pos = c1pos;
    this.c2SYMD = c2symd;
    this.c2EYMD = c2eymd;
    this.c2kikanView = c2kikanView;
    this.carrier2 = carrier2;
    this.c2pos = c2pos;
    this.c3SYMD = c3symd;
    this.c3EYMD = c3eymd;
    this.c3kikanView = c3kikanView;
    this.carrier3 = carrier3;
    this.c3pos = c3pos;
  }
  
  //アクセサメソッド
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public String getSikaku1() {
    return sikaku1;
  }
  public void setSikaku1(String sikaku1) {
    this.sikaku1 = sikaku1;
  }
  public LocalDateTime getSikaku1YMD() {
    return sikaku1YMD;
  }
  public void setSikaku1YMD(LocalDateTime sikaku1ymd) {
    sikaku1YMD = sikaku1ymd;
  }
  public String getSikaku2() {
    return sikaku2;
  }
  public void setSikaku2(String sikaku2) {
    this.sikaku2 = sikaku2;
  }
  public LocalDateTime getSikaku2YMD() {
    return sikaku2YMD;
  }
  public void setSikaku2YMD(LocalDateTime sikaku2ymd) {
    sikaku2YMD = sikaku2ymd;
  }
  public String getSikaku3() {
    return sikaku3;
  }
  public void setSikaku3(String sikaku3) {
    this.sikaku3 = sikaku3;
  }
  public LocalDateTime getSikaku3YMD() {
    return sikaku3YMD;
  }
  public void setSikaku3YMD(LocalDateTime sikaku3ymd) {
    sikaku3YMD = sikaku3ymd;
  }
  public LocalDateTime getC1SYMD() {
    return c1SYMD;
  }
  public void setC1SYMD(LocalDateTime c1symd) {
    c1SYMD = c1symd;
  }
  public LocalDateTime getC1EYMD() {
    return c1EYMD;
  }
  public void setC1EYMD(LocalDateTime c1eymd) {
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
  public LocalDateTime getC2SYMD() {
    return c2SYMD;
  }
  public void setC2SYMD(LocalDateTime c2symd) {
    c2SYMD = c2symd;
  }
  public LocalDateTime getC2EYMD() {
    return c2EYMD;
  }
  public void setC2EYMD(LocalDateTime c2eymd) {
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
  public LocalDateTime getC3SYMD() {
    return c3SYMD;
  }
  public void setC3SYMD(LocalDateTime c3symd) {
    c3SYMD = c3symd;
  }
  public LocalDateTime getC3EYMD() {
    return c3EYMD;
  }
  public void setC3EYMD(LocalDateTime c3eymd) {
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
  public String getC1kikanView() {
    return c1kikanView;
  }
  public void setC1kikanView(String c1kikanView) {
    this.c1kikanView = c1kikanView;
  }
  public String getC2kikanView() {
    return c2kikanView;
  }
  public void setC2kikanView(String c2kikanView) {
    this.c2kikanView = c2kikanView;
  }
  public String getC3kikanView() {
    return c3kikanView;
  }
  public void setC3kikanView(String c3kikanView) {
    this.c3kikanView = c3kikanView;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDateTime getNyuusyaYMD() {
    return nyuusyaYMD;
  }

  public void setNyuusyaYMD(LocalDateTime nyuusyaYMD) {
    this.nyuusyaYMD = nyuusyaYMD;
  }

  public String getNyuusyaYMDView() {
    return nyuusyaYMDView;
  }

  public void setNyuusyaYMDView(String nyuusyaYMDView) {
    this.nyuusyaYMDView = nyuusyaYMDView;
  }

   
}