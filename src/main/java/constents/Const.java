package constents;

public class Const {

  //共通
  public static final class Common {

    public static final String ENCODE_UTF8 = "UTF-8";
    public static final String HASH_MODE = "SHA-256";

    //DB関連
    public static final String DRIVER_NAME = "drivername";
    public static final String JDBC_URL = "jdbcurl";
    public static final String DB_USER = "username";
    public static final String DB_PASS = "userpassword";
    public static final String DB_PROP_PATH = "C:\\pleiades\\2023-06\\workspace\\TDSLWebProject\\src\\main\\webapp\\WEB-INF\\lib\\DBAccess.properties";
    public static final String SQL_FILE_PATH = "C:\\pleiades\\2023-06\\workspace\\TDSLWebProject\\src\\main\\webapp\\WEB-INF\\sql\\";

    //その他
    public static final String SHINKI = "1";
    public static final String KISON = "0";

  }

  //遷移関連
  public static final class Path {

    //画面名
    public static final String LOGIN_GAMEN = "/login.jsp";
    public static final String MAIN_GAMEN = "/index.jsp";
    public static final String SYSTEM_ERROR_GAMEN = "/error.jsp";
    public static final String MAIN_GAMEN_PATH = "/TDSLWebProject/index.jsp";
    public static final String SYAIN_JOUHOU_PATH = "/src/syainJouhou.jsp";
    public static final String SYAIN_HENSYU_PATH = "/src/syainJouhouHensyu.jsp";
    public static final String MASTA_GAMEN = "/masta.jsp";
    public static final String USER_ADD_GAMEN = "/src/userAddMasta.jsp";
    public static final String UPDATE_PASSWORD_GAMEN = "/updatePassword.jsp";
    public static final String USER_ICHIRAN_GAMEN = "/src/userIchiran.jsp";
    public static final String USER_DEL_GAMEN = "/src/userDelMasta.jsp";
    public static final String USER_UPD_GAMEN = "/src/userUpdMasta.jsp";
    public static final String RESULT_USER_UPD_GAMEN = "/src/resultUserUpdMasta.jsp";
    public static final String KARI_USER_GAMEN = "/src/karitouroku.jsp";

    //スコープ名
    public static final String SESSION_SCOPE = "loginSession";
    public static final String SYAIN_JOUHOU_SCOPE = "syainJouhou";
    public static final String SYAIN_HENSYU_SCOPE = "syainHensyu";
    public static final String BEFORE_ACTION = "LoginAction";
    public static final String BEFORE_LOGIN = "loginBefore";
    public static final String USER_ATTRIBUTE = "userAttribute";
    public static final String USER_ICHIRAN_SCOPE = "userIchiran";
    public static final String USER_HENSYU_SCOPE = "userHensyu";

  }

  //正常系メッセージ関連
  public static final class MSG {
    public static final String MSG_ATTRIBUTE = "MSG";
  }

  //エラー関連
  public static final class ERRORMSG {
    public static final String ERRMSG_ATTRIBUTE = "ERRMSG";
    public static final String ERR_1 = "IDを入力してください";
    public static final String ERR_2 = "パスワードを入力してください";
    public static final String ERR_3 = "入力されたIDは存在しません";
    public static final String ERR_4 = "パスワードが異なります";
    public static final String ERR_5 = "この操作には管理者権限が必要です";
    public static final String ERR_6 = "ユーザーIDが重複しています";
  }

}
