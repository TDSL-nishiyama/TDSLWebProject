package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckCommon {

  public boolean checkDate(String pDate) {
    boolean result = true;

    if (pDate == null || pDate.equals("")) {
      return result;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    try {
      sdf.setLenient(false);
      sdf.parse(pDate);
    } catch (ParseException e) {
      result = false;
    }
    return result;
  }

  public boolean checkBlankOrNULL(String str) {
    boolean result = true;

    if (str == null || str.trim().equals("")) {
      result = false;
    }

    return result;
  }
}
