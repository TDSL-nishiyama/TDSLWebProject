package constents;

public class Const {

	//共通
	public static final class Common{

		public static final String ENCODE_UTF8 = "UTF-8";

	}


	//遷移関連
	public static final class Path {

		public static final String SESSION_SCOPE_NAME = "loginSession";
		public static final String LOGIN_GAMEN = "/login.jsp";
		public static final String MAIN_GAMEN = "/index.jsp";
		public static final String SYSTEM_ERROR_GAMEN = "/error.jsp";
		public static final String MAIN_GAMEN_PATH = "/TDSLWebProject/index.jsp";
		public static final String SYAIN_JOUHOU_PATH = "./src/ResultSyainJouhou.jsp";
		public static final String SYAIN_JOUHOU_SCOPE = "syainJouhouEntityList";
		public static final String MASTA_GAMEN = "/masta.jsp";
		public static final String USER_ADD_GAMEN = "./src/userAddMasta.jsp";

	}

	//エラー関連
	public static final class ERRORMSG {
	    public static final String ERR_1 = "IDを入力してください";
	    public static final String ERR_2 = "パスワードを入力してください";
	    public static final String ERR_3 = "入力されたIDは存在しません";
	    public static final String ERR_4 = "パスワードが異なります";
	    public static final String ERR_5 = "この操作には管理者権限が必要です";
	}

}
