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

    if (pDate == null || pDate.isBlank()) {
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
   * {@index　日付型文字列をLocalDateに変換する}
   * @param pStr
   * @return
   */
  public LocalDate chgStrtoLD(String pStr) {
    LocalDate result = null;

    if (pStr == null || pStr.isBlank()) {
      return result;
    }

    result = chgDtoLD(chgStrToDate(pStr));

    return result;
  }
  
  /**
   * {@index　日付型文字列をLocalDateTimeに変換する}
   * @param pStr
   * @return
   */
  public LocalDateTime chgStrtoLDT(String pStr) {
    LocalDateTime result = null;

    if (pStr == null || pStr.isBlank()) {
      return result;
    }

    result = chgDtoLDT(chgStrToDate(pStr));

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
   * {@index LocalDateをStringに変換する（yyyy-MM-dd形式）}
   * @param ldt LocalDateTime型の値
   * @return
   */
  public String chgLDtoStr(LocalDate ld) {
    if (ld == null) {
      return null;
    }

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    return ld.format(dateTimeFormatter);
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
   * {@index DateをLocalDateTimeに変換する}
   * @param d DB源泉の値はDate型でキャストしないとjava.sql.toInstant()が呼ばれて
   * UnsupportedOperationExceptionが発生するためNEWして渡すこと
   * @return 
   */
  public LocalDateTime chgDtoLDT(Date d) {

    LocalDate ld = LocalDate.ofInstant(d.toInstant(), ZoneId.systemDefault());
    LocalTime lt = LocalTime.ofInstant(d.toInstant(), ZoneId.systemDefault());

    return LocalDateTime.of(ld, lt);

  }

  /**
   * {@index DateをLocalDateに変換する}
   * @param d DB源泉の値はDate型でキャストしないとjava.sql.toInstant()が呼ばれて
   * UnsupportedOperationExceptionが発生するためNEWして渡すこと
   * @return 
   */
  public LocalDate chgDtoLD(Date d) {

    LocalDate ld = LocalDate.ofInstant(d.toInstant(), ZoneId.systemDefault());

    return ld;

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
  
  /**
   * {@index 入力値がブランクまたはNULLの場合入力値にNULLを代入して返却する}
   * @param input
   * @return
   */
  public String setNull(Object input) {
    CheckCommon checkC = new CheckCommon();
    String result = (String) input;
    if(checkC.checkBlankOrNULL(result)==false) {
      result = null;
    }
    return result;
  }
  
  /**
   * {@index 改行コード\nを<br>タグに変換する}
   * @param str　変換したい文字列
   * @return
   */
  public String chgKaigyouCode(String str) {
    String result = null;
    CheckCommon checkC = new CheckCommon();
    if (checkC.checkBlankOrNULL(str) == true) {
      result = str.replace("\n", "<br>");
    } else {
      result = "";
    }
    return result;
  }
  
  /**
   * {@index <br>タグを改行コード\nに変換する}
   * @param str　変換したい文字列
   * @return
   */
  public String chgBRtag(String str) {
    String result = null;
    CheckCommon checkC = new CheckCommon();
    if (checkC.checkBlankOrNULL(str) == true) {
      result = str.replace("<br>", "\n");
    } else {
      result = "";
    }
    return result;
  }
}
