package model;

import java.io.Serializable;

public class SessionKanriBean implements Serializable {
	
	private int userId; //ユーザーID
    private String loginId; //ログインID
    private String loginName; //ログイン名
    private boolean kanriFlg; //管理フラグ("TRUE"=管理者権限あり)
    private String sakujo; //(1=退職)

    //コンストラクタ
    public SessionKanriBean() {

    }

    //コンストラクタ（id）
    public SessionKanriBean(int userId) {
        this.userId = userId;
    }

    //コンストラクタ（id,loginid,名前,管理者フラグ）
    public SessionKanriBean(int userId,String loginId,String loginName,boolean kanriFlg) {
    	this.userId = userId;
        this.loginId = loginId;
        this.loginName = loginName;
        this.kanriFlg = kanriFlg;
    }

    //アクセサメソッド
    public int getUserId(int userId) {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String id) {
        this.loginId = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public boolean getKanriFlg() {
        return kanriFlg;
    }

    public void setKanriFlg(boolean kanriFlg) {
        this.kanriFlg = kanriFlg;
    }

	public String getSakujo() {
		return sakujo;
	}

	public void setSakujo(String sakujo) {
		this.sakujo = sakujo;
	}

}
