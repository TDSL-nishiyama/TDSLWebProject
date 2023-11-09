package control.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class CalcCommon {

  public Period diffDate(Date date) {
    
    Period result = null;
    
    String strDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
    String[] splYMD = strDate.split("/");
    LocalDate ldate = LocalDate.of(Integer.parseInt(splYMD[0]),Integer.parseInt(splYMD[1]),Integer.parseInt(splYMD[2]));
    result = Period.between(ldate, LocalDate.now());
    
    return result;
    
  }
  
}
