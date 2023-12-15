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
    public static final String MAIN_GAMEN_PATH = "/TDSLWebProject/index.jsp";
    public static final String LOGIN_GAMEN = "/login.jsp";
    public static final String MAIN_GAMEN = "/index.jsp";
    public static final String SYSTEM_ERROR_GAMEN = "/error.jsp";
    public static final String SYAIN_JOUHOU_GAMEN = "/src/syainJouhou.jsp";
    public static final String SYAIN_HENSYU_GAMEN = "/src/syainJouhouHensyu.jsp";
    public static final String MASTA_GAMEN = "/masta.jsp";
    public static final String USER_ADD_GAMEN = "/src/userAddMasta.jsp";
    public static final String UPDATE_PASSWORD_GAMEN = "/updatePassword.jsp";
    public static final String USER_ICHIRAN_GAMEN = "/src/userIchiran.jsp";
    public static final String USER_DEL_GAMEN = "/src/userDelMasta.jsp";
    public static final String USER_UPD_GAMEN = "/src/userUpdMasta.jsp";
    public static final String RESULT_USER_UPD_GAMEN = "/src/resultUserUpdMasta.jsp";
    public static final String KARI_USER_GAMEN = "/src/karitouroku.jsp";
    public static final String KOUTSUU_YOUKYUU_GAMEN = "/src/koutsuuYoukyuu.jsp";
    public static final String KOUTSUU_KAKUNIN_GAMEN = "/src/koutsuuKakunin.jsp";
    public static final String KOUTSUU_SHOUNIN_GAMEN = "/src/koutsuuShounin.jsp";
    public static final String KOUTSUU_SASHIMODOSHI_GAMEN = "/src/koutsuuSashimodoshi.jsp";
    public static final String KOUTSUU_SYUUSEI_GAMEN = "/src/koutsuuSyuusei.jsp";
    public static final String MASTA_MAIL_HOME_GAMEN = "/src/mail.jsp";
    public static final String MASTA_MAIL_UPD_GAMEN = "/src/mailUpd.jsp";
    
    
    //スコープ名
    public static final String SESSION_SCOPE = "loginSession";
    
    public static final String SYAIN_JOUHOU_SCOPE = "syainJouhou";
    public static final String SYAIN_HENSYU_SCOPE = "syainHensyu";
    public static final String BEFORE_ACTION = "LoginAction";
    public static final String BEFORE_LOGIN = "loginBefore";
    public static final String USER_ATTRIBUTE = "userAttribute";
    public static final String USER_ICHIRAN_SCOPE = "userIchiran";
    public static final String USER_ADD_SCOPE = "userAdd";
    public static final String USER_UPD_SCOPE = "userUpd";
    public static final String USER_HENSYU_SCOPE = "userHensyu";
    public static final String KOUTSUU_YOUKYU_SCOPE = "koutsuuYoukyu";
    public static final String KOUTSUU_KAKUNIN_SCOPE = "koutsuuKakunin";
    public static final String KOUTSUU_SHOUNIN_SCOPE = "koutsuuShounin";
    public static final String MAIL_SCOPE = "mailMasta";
  }

  //正常系メッセージ関連
  public static final class MSG {
    //スコープ名
    public static final String MSG_ATTRIBUTE = "MSG";
    
    //汎用
    public static final String MSG_DATE_INTEGRITY_ERR_1 = "入社日付もしくは生年月日が誤っています。日付を再度確認してください";
    public static final String MSG_DATE_INTEGRITY_ERR_2 = "１６才未満の就労は不可能です。生年月日を確認してください。";
    
    //パスワード登録更新
    public static final String UPDPASS_1 = "ユーザーの登録が完了しました。再度ログインをお願いします";
    public static final String UPDPASS_2 = "パスワードの更新が完了しました";
    
    //社員情報
    public static final String SYAIN1 = "一般ユーザーは自分のID以外は更新することができません";
    
    //マスタ_登録
    public static final String MASTA_ADD_1_1 = "必須項目（";
    public static final String MASTA_ADD_1_2 = "）を入力してください";
    public static final String MASTA_ADD_2 = "ユーザーの登録が完了しました";
    
    //マスタ_更新
    public static final String MASTA_UPD_1 = "入力されたIDは存在しません";
    public static final String MASTA_UPD_2_1 = "必須項目（";
    public static final String MASTA_UPD_2_2 = "）を入力してください";
    public static final String MASTA_UPD_3 = "現在ログインしているユーザーの管理者権限は変更できません";
    
    //マスタ_削除
    public static final String MASTA_DEL_1 = "入力されたIDは存在しません";
    public static final String MASTA_DEL_2 = "現在ログインしているユーザーは削除できません";
    public static final String MASTA_DEL_3 = "ユーザーの削除が完了しました";
    
    //交通費精算要求
    public static final String K_YOUKYU_1 = "交通費の申請が完了しました。申請状況は交通費精算確認画面で確認してください";
    
    //交通費承認
    public static final String K_SHONIN_1 = "IDを入力してください";
    public static final String K_SHONIN_2 = "入力されたIDは存在しません";
    public static final String K_SHONIN_3 = "更新が完了しました";
    
    //交通費差戻
    public static final String K_SASHIMODOSHI_1 = "差戻理由を記載してください";
    public static final String K_SASHIMODOSHI_2 = "差戻処理が完了しました";
    
    //交通費修正
    public static final String K_SYUUSEI_1 = "修正処理が完了しました";
  }

  //エラー関連
  public static final class ERRORMSG {
    public static final String ERRMSG_ATTRIBUTE = "ERRMSG";
    //汎用
    public static final String DBERROR = "テーブル更新が正常に行われませんでした";
    public static final String DBERROR_SEL = "テーブル取得が正常に行われませんでした";
    public static final String ERROR_SETLENIENT = "存在しない日付です";
    
    //ログイン・パスワード登録更新
    public static final String ERR_1 = "IDを入力してください";
    public static final String ERR_2 = "パスワードを入力してください";
    public static final String ERR_3 = "入力されたIDは存在しません";
    public static final String ERR_4 = "パスワードが異なります";
    public static final String ERR_5 = "この操作には管理者権限が必要です";
    public static final String ERR_6 = "ログインIDは5桁以上にしてください";
    public static final String ERR_7 = "ユーザーIDが重複しています";
    public static final String ERR_8 = "ログインIDの先頭はkari以外にしてください";
    
  }

}
