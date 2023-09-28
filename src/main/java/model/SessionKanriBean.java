package model;

import java.io.Serializable;

public class SessionKanriBean implements Serializable {

    private String loginId; //ログインID
    private String password; //ログインパスワード
    private String loginName; //ログイン名
    private boolean kanriFlg; //管理フラグ("TRUE"=管理者権限あり)

    //コンストラクタ
    public SessionKanriBean() {

    }

    //コンストラクタ（id）
    public SessionKanriBean(String id) {
        this.loginId = id;
    }

    //コンストラクタ（id,password）
    public SessionKanriBean(String id, String password) {
        this.loginId = id;
        this.password = password;
    }

    //コンストラクタ（id,password,名前,管理者フラグ）
    public SessionKanriBean(String id, String password,String loginName,boolean kanriFlg) {
        this.loginId = id;
        this.password = password;
        this.loginName = loginName;
        this.kanriFlg = kanriFlg;
    }

    //アクセサメソッド
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String id) {
        this.loginId = id;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
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

}
