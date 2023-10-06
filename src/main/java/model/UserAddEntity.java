package model;

public class UserAddEntity {

	private int userid;
	private String name;
	private boolean kanriFlg;
	private String loginid;
	private String loginpassword;
	
	public UserAddEntity(int userid,String name, boolean kanriFlg) {
		this.userid = userid;
		this.name = name;
		this.kanriFlg = kanriFlg;
	}
	
	public UserAddEntity(int userId,String loginid,String loginpassword) {
		this.loginid = loginid;
		this.loginpassword = loginpassword;
	}
	
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
