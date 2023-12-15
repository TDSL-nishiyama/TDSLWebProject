package constents;

public class Table {
  /**
   * @see ユーザーテーブルの項目
   */
  public static final class User {
    public final static String COL_USERID = "id";
    public final static String COL_USERNAME = "name";
    public final static String COL_KANRIFLG = "kanriflg";
    public final static String COL_DEL = "del";
  }
  
  /**
   * @see ログインテーブルの項目
   */
  public static final class Login{
    public final static String COL_USERID = "id";
    public final static String COL_LOGINID = "loginid";
    public final static String COL_LOGINPASSWORD = "password";
    public final static String COL_DEL = "del";
  }
  /**
   * @see メールテーブルの項目
   */
  public static final class Mail{
    public final static String COL_USERID = "id";
    public final static String COL_MAILADDRESS = "mailaddress";
    public final static String COL_TIMESTAMP = "timestamp";
  }
  
}
