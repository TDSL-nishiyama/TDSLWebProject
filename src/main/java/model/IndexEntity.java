package model;

public class IndexEntity {

	private String loginId;			//ログインID
	private String password; 	//ログインパスワード
	private boolean kanriFlg;   	//管理フラグ(true=管理者権限あり　false=管理者権限なし)

	//コンストラクタ
	public IndexEntity(){

	}

	//コンストラクタ（loginId）
	public IndexEntity(String loginId){
		this.loginId = loginId;
	}

	//コンストラクタ（loginId,password）
	public IndexEntity(String loginId,String password){
		this.loginId = loginId;
		this.password = password;
	}

	//アクセサメソッド
	public String getLoginId(){
		return loginId;
	}

	public void setLoginId(String id){
		this.loginId = id;
	}

	public String getpassword(){
		return password;
	}

	public void setpassword(String password){
		this.password = password;
	}

	public boolean isKanriFlg() {
	    return kanriFlg;
	}

	public void setKanriFlg(boolean kanriFlg) {
	    this.kanriFlg = kanriFlg;
	}

}
