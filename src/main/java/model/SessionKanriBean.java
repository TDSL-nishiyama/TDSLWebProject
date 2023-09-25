package model;

import java.io.Serializable;

public class SessionKanriBean implements Serializable {

    private String loginId; //ログインID
    private String loginPassword; //ログインパスワード
    private String loginName; //ログイン名
    private String kanriFlg; //管理フラグ("1"=管理者権限あり)

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
        this.loginPassword = password;
    }

    //コンストラクタ（id,password,名前,管理者フラグ）
    public SessionKanriBean(String id, String password,String loginName,String kanriFlg) {
        this.loginId = id;
        this.loginPassword = password;
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

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getKanriFlg() {
        return kanriFlg;
    }

    public void setKanriFlg(String kanriFlg) {
        this.kanriFlg = kanriFlg;
    }

}
