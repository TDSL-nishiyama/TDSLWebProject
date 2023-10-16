package model;

import java.io.Serializable;

public class MastaEntity implements Serializable{

	private int userid;
	private String name;
	private boolean kanriFlg;
	private String sakujo; //(1=退職)
	private String loginid;
	private String loginpassword;
	
	//コンストラクタ
	public MastaEntity(int userid,String name, boolean kanriFlg,String loginid,String loginpassword) {
		this.userid = userid;
		this.name = name;
		this.kanriFlg = kanriFlg;
		this.loginid = loginid;
		this.loginpassword = loginpassword;
	}
	
	public MastaEntity(int userid,String name, boolean kanriFlg,String loginid) {
		this.userid = userid;
		this.name = name;
		this.kanriFlg = kanriFlg;
		this.loginid = loginid;
	}
	
	public MastaEntity(int userid,String sakujo) {
		this.userid = userid;
		this.sakujo = sakujo;
	}
	
	//アクセサメソッド
	public int getUserid() {
		return userid;
	}
	public void setUseridU(int useridU) {
		this.userid = useridU;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getLoginpassword() {
		return loginpassword;
	}
	public void setLoginpassword(String loginpassword) {
		this.loginpassword = loginpassword;
	}
}
