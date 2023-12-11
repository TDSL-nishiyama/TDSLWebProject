package control.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CastCommon {

  /**
   * {@index StringをDateに変換する}
   * @param pDate
   * @return ParseExceptionが発生した場合NULLを設定
   */
  public Date chgStrToDate(String pDate) {
    Date result = null;

    if (pDate == null || pDate.equals("")) {
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
  public String chgDStrToStr(String pDate) {
    String result = null;

    if (pDate == null) {
      return result;
    }

    final String[] formatStrings = { "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd" };

    Date date = null;

    for (String formatString : formatStrings) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        sdf.setLenient(false);
        date = sdf.parse(pDate);
        //dateが変換できた場合ループから抜ける
        if (date != null) {
          //日付項目を内部持ち回り形式に変換
          result = new SimpleDateFormat("yyyy-MM-dd").format(date);
          break;
        }
        //日付変換エラー・パラメータ想定外エラーの場合は何もせず値を返却
      } catch (ParseException | IllegalArgumentException e) {
        result = pDate;
      }
    }

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
  
  /**
   * {@index LocalDateTimeをStringに変換する（yyyy-MM-dd形式）}
   * @param ldt LocalDateTime型の値
   * @return
   */
  public String chgLDTtoStr(LocalDateTime ldt) {
    if (ldt == null) {
      return null;
    }
    
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    return ldt.format(dateTimeFormatter);
  }
  
  /**
   * {@index DateTimeをLocalDateTimeに変換する}
   * @param d DB厳選の値はDate型でキャストしないとjava.sql.toInstant()が呼ばれて
   * UnsupportedOperationExceptionが発生するためNEWして渡すこと
   * @return 
   */
  public LocalDateTime chgDtoLD(Date d) {
    
    LocalDate ld = LocalDate.ofInstant(d.toInstant(), ZoneId.systemDefault());
    LocalTime lt = LocalTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
    
    return LocalDateTime.of(ld, lt);
    
  }

  /**
   * {@index NULLをブランクに変換する}
   * @param input　String
   * @return
   */
  public String nullToBlank(String input) {
    String result = null;

    if (input == null) {
      result = "";
    } else {
      result = input;
    }

    return result;

  }
}
