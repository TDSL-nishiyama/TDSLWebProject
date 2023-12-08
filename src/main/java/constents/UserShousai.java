package constents;

import java.util.Map;

public class UserShousai {

  public final static String GAMEN_SEI = "sei";
  public final static String GAMEN_MEI = "mei";
  public final static String GAMEN_SEIYOMI = "sei_yomi";
  public final static String GAMEN_MEIYOMI = "mei_yomi";
  public final static String GAMEN_NYUUSYAYMD = "nyuusyaYMD";
  public final static String GAMEN_SEIBETSU = "seibetsu";
  public final static String GAMEN_SEINENNGAPPI = "seinenngappi";
  public final static String GAMEN_SYUSSSHIN = "syusshin";
  public final static String GAMEN_JUUSYO = "juusyo";

  public final static String COL_ID = "id";
  public final static String COL_SEI = "sei";
  public final static String COL_SEI_YOMI = "sei_yomi";
  public final static String COL_MEI = "mei";
  public final static String COL_MEI_YOMI = "mei_yomi";
  public final static String COL_NYUUSYAYMD = "nyuusyaYMD";
  public final static String COL_TAISYAYMD = "taisyaYMD";
  public final static String COL_SEIBETSU = "seibetsu";
  public final static String COL_SEINENGAPPI = "seinenngappi";
  public final static String COL_SYUSSHIN = "syusshin";
  public final static String COL_JYUUSYO = "juusyo";
  
  //管理者ユーザーの必須項目チェック用
  public static void hissuKoumokuPut(Map<String, String> hissuKoumoku) {
    hissuKoumoku.put(COL_SEI, "姓");
    hissuKoumoku.put(COL_MEI, "名");
    hissuKoumoku.put(COL_SEI_YOMI, "姓(ﾖﾐ)");
    hissuKoumoku.put(COL_MEI_YOMI, "名(ﾖﾐ)");
    hissuKoumoku.put(COL_SEIBETSU, "性別");
    hissuKoumoku.put(COL_SEINENGAPPI, "生年月日");
    hissuKoumoku.put(COL_JYUUSYO, "住所");
  }
  
  //一般ユーザーの必須項目チェック用
  public static void hissuKoumokuPutIppan(Map<String, String> hissuKoumoku) {
    hissuKoumoku.put(COL_SEI, "姓");
    hissuKoumoku.put(COL_MEI, "名");
    hissuKoumoku.put(COL_SEI_YOMI, "姓(ﾖﾐ)");
    hissuKoumoku.put(COL_MEI_YOMI, "名(ﾖﾐ)");
    hissuKoumoku.put(COL_JYUUSYO, "住所");
  }

}
