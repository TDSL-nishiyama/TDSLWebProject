package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastCommon {
  
  final String MOJIRETSU = "[^0-9]";
  
  public String removeAllNonAlphaNumeric(String str) {
    if (str == null) {
        return null;
    }
    //数値以外を消去
    return str.replaceAll(MOJIRETSU, "");
}
  
  public Date chgStrToDate(String pDate){
    Date result = null;
    
    if (pDate == null||pDate.equals("")) {
      return null;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      result = sdf.parse(pDate);
    } catch (ParseException e) {
      result = null;
    }
    
    return result;
  }
  
  /**
   * {@index 画面に入力された日付形式項目を内部用持ち回り形式yyyy-MM-ddに変換する}
   * @param pDate　String
   * @return
   */
  public String chgDateToStr(String pDate) {
    String result = null;
    
    if (pDate == null) {
      return result;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = sdf.parse(pDate);
    } catch (ParseException e) {
      return null;
    }
    result = sdf.format(date);
    
    return result;
  }
  
  /**
   * {@index Date型をStringに変換する（yyyy-MM-dd形式）}
   * @param pDate　String
   * @return
   */
  public String chgDateToStr(Date pDate) {
    String result = null;
    
    if (pDate == null) {
      return result;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    result = sdf.format(pDate);
    
    return result;
  }
    
  public String nullToBlank(String input) {
    String result = null;
    
    if(input == null) {
      result = "";
    }else {
      result = input;
    }
    
    return result;
    
  }
}
