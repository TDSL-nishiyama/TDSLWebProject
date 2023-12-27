package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class CalcCommon {
	
	/**
	 *{@index} 日付計算（年月日）
	 *@param Date date 日付 
	 *@return Period パラメータに設定した日付-現在日付
	 **/
	public Period diffDate(Date date) {

		Period result = null;
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String[] splYMD = strDate.split("-");
		LocalDate ldate = LocalDate.of(Integer.parseInt(splYMD[0]), Integer.parseInt(splYMD[1]),
				Integer.parseInt(splYMD[2]));
		result = Period.between(ldate, LocalDate.now());

		return result;

	}
	
	 /**
   *{@index} 日付計算（年月日）
   *@param LocalDateTime date 日付 
   *@return Period パラメータに設定した日付-現在日付
   **/
  public Period diffDate(LocalDateTime date) {
    Period result = null;
    LocalDate ldate = LocalDate.of(date.getYear(),date.getMonth(),date.getDayOfMonth());
    result = Period.between(ldate, LocalDate.now());

    return result;

  }
	
	 /**
   *{@index} 日付計算（年月日）
   *@param String date1 日付1
   *@param String date2 日付2
   *@return Period pDate1-pDate2
   **/
  public Period diffDate(String pDate1,String pDate2) {
    
    //入力値をdate型に変換
    Date date1 = null;
    Date date2 = null;
    try {
      date1 = new SimpleDateFormat("yyyy-MM-dd").parse(pDate1);
      date2 = new SimpleDateFormat("yyyy-MM-dd").parse(pDate2);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    Period result = null;
    String strDate1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
    String strDate2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
    String[] splYMD1 = strDate1.split("-");
    String[] splYMD2 = strDate2.split("-");
    
    LocalDate ldate1 = LocalDate.of(Integer.parseInt(splYMD1[0]), Integer.parseInt(splYMD1[1]),
        Integer.parseInt(splYMD1[2]));
    LocalDate ldate2 = LocalDate.of(Integer.parseInt(splYMD2[0]), Integer.parseInt(splYMD2[1]),
        Integer.parseInt(splYMD2[2]));
    result = Period.between(ldate1, ldate2);

    return result;

  }
  
  /**
  *{@index} 日付計算（年月日）
  *@param LocalDateTime date1 日付1
  *@param LocalDateTime date2 日付2
  *@return Period pDate1-pDate2
  **/
 public Period diffDate(LocalDateTime pDate1,LocalDateTime pDate2) {
  
   Period result = null;

   LocalDate ldate1 = LocalDate.of(pDate1.getYear(), pDate1.getMonth(),pDate1.getDayOfMonth());
   LocalDate ldate2 = LocalDate.of(pDate2.getYear(), pDate2.getMonth(),pDate2.getDayOfMonth());
   result = Period.between(ldate1, ldate2);

   return result;

 }

}
