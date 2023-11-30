package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CheckCommon {

  public boolean checkDate(String pDate) {
    boolean result = true;

    if (pDate == null || pDate.equals("")) {
      return result;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
  
  public int checkUserId(int userId) {
    
    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(userId);
    DAOCommon dao = new DAOCommon();
    result = dao.countSQL("checkUserId.sql", statement);

    return result;
  }
  
  public int checkUserIdAll(int userId) {
    
    int result = 0;
    List<Object> statement = new ArrayList<>();
    statement.add(userId);
    DAOCommon dao = new DAOCommon();
    result = dao.countSQL("checkUserIdAll.sql", statement);

    return result;
  }

}
