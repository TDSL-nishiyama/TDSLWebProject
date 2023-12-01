package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastCommon {
  
  public Date chgStrToDate(String pDate) throws ParseException {
    Date result = null;
    
    if (pDate == null||pDate.equals("")) {
      return result;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    result = sdf.parse(pDate);
    
    return result;
  }
  
  public String chgDateToStr(Date pDate) {
    String result = null;
    boolean errflg = false;
    
    if (pDate == null) {
      return result;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    result = sdf.format(pDate);
    
    CheckCommon checkCommon = new CheckCommon();
    errflg = checkCommon.checkDate(result);
    if(errflg == false) {
      result = null;
    }
    
    return result;
  }
  
  /**
   * {@index 画面に入力された日付形式項目を内部用持ち回り形式yyyy-MM-ddに変換する}
   * @param pDate　String
   * @return
   */
  public String chgGamenDateToStr(String pDate) {
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
