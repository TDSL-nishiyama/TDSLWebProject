package constents;

public class KoutsuuConst {
  /**
   * @see 交通系画面共通定数
   */
  public static class KCommon{
   public final static String QUERY_TYPE_0_SELID = "0"; 
   public final static String QUERY_TYPE_1_ALL = "1"; 
   public final static String QUERY_TYPE_2_SELSTA = "2";
   public final static String QUERY_TYPE_3_JOGAI = "3";
   
   public final static int NONSELID = 0;
   public final static String NONSELSTA = null;
   
   public final static String SHINSEI = "0";
   public final static String SASHIMODOSHI = "1";
   public final static String SYOUNIN = "2";
   public final static String FURIKOMIZUMI = "3";
   
  }
  /**
   * @see 交通テーブルの項目
   */
  public static final class Koutsuu {
    public final static String COL_UNINO = "no";
    public final static String COL_USERID = "id";
    public final static String COL_NAME = "name";
    public final static String COL_SMAIL = "sendmailaddress";
    public final static String COL_RIYOUHIDUKE = "riyouhiduke";
    public final static String COL_KUKAN_S = "kukan_start";
    public final static String COL_KUKAN_E = "kukan_end";
    public final static String COL_KINGAKU = "kingaku";
    public final static String COL_BIKOU = "bikou";
    public final static String COL_MODOSHIRIYUU = "modoshiriyuu";
  }
  /**
   * @see 交通時刻テーブルの項目
   */
  public static final class KtimeStamp{
    public final static String COL_UNINO = "no";
    public final static String COL_YOUKYUU = "youkyuu";
    public final static String COL_SASHIMODOSHI = "sashimodoshi";
    public final static String COL_SYONIN = "syounin";
    public final static String COL_STATUS = "status";
    public final static String COL_TIMESTAMP = "timestamp";
  }
  /**
   * @see 交通費精算要求画面の項目
   */
  public static final class YoukyuuG{
    public final static String GAMEN_RIYOUHIDUKE_1 = "riyou1";
    public final static String GAMEN_KUKAN_START_1 = "kukans1";
    public final static String GAMEN_KUKAN_END_1 = "kukane1";
    public final static String GAMEN_KINGAKU_1 = "kingaku1";
    public final static String GAMEN_BIKOU_1 = "bikou1";
    public final static String GAMEN_RIYOUHIDUKE_2 = "riyou2";
    public final static String GAMEN_KUKAN_START_2 = "kukans2";
    public final static String GAMEN_KUKAN_END_2 = "kukane2";
    public final static String GAMEN_KINGAKU_2 = "kingaku2";
    public final static String GAMEN_BIKOU_2 = "bikou2";
  }
  /**
   * @see 交通費精算承認画面の項目
   */
  public static final class ShouninG{
    public final static String GAMEN_RIYOUHIDUKE = "riyou";
    public final static String GAMEN_KUKAN_START = "kukans";
    public final static String GAMEN_KUKAN_END = "kukane";
    public final static String GAMEN_KINGAKU = "kingaku";
    public final static String GAMEN_BIKOU = "bikou";
  }
  /**
   * @see 交通費差戻画面の項目
   */
  public static final class SashimodoshiG{
    public final static String GAMEN_MODOSHIRIYUU = "modoshiriyuu";
  }
}
