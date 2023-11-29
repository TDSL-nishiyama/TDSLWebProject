package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CastCommon {
  
  public Date chgStrToDate(String pDate) {
    Date result = null;
    
    if (pDate == null||pDate.equals("")) {
      return result;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      result = sdf.parse(pDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
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
