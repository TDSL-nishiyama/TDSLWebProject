package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.IndexEntity;
import model.SessionKanriBean;

public class IndexDAO extends DBConnection {

    public String findLoginId(String pLoginId) {

        Connection conn = null;
        String loginId = null;

        try {

            // JDBCドライバを読み込み
            super.loadJDBCDriver();

            // DBへ接続
            conn = super.connectionDB(conn);

            // SELECT文を準備
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT loginid,loginpass");
            sql.append(" FROM login");
            sql.append(" WHERE loginid = ?;");

            PreparedStatement pStmt = conn.prepareStatement(sql.toString());
            pStmt.setString(1, pLoginId);

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                loginId = rs.getString("loginid");
            }

            IndexEntity indexEntity = new IndexEntity(loginId);

            indexEntity.setLoginId(loginId);

            loginId = indexEntity.getLoginId();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            super.closeDB(conn);
        }

        return loginId;
    }

    public List<String> findLoginIdAndPassword(String pLoginId,
            String pLoginPassword) {

        Connection conn = null;
        List<String> indexEntityList = new ArrayList<>();

        try {
            // JDBCドライバ読み込み
            super.loadJDBCDriver();

            // DBへ接続
            conn = super.connectionDB(conn);

            // SELECT文を準備
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT loginid,loginpass");
            sql.append(" FROM login");
            sql.append(" WHERE loginid = ? AND loginpass = ?;");

            PreparedStatement pStmt = conn.prepareStatement(sql.toString());
            pStmt.setString(1, pLoginId);
            pStmt.setString(2, pLoginPassword);

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            String loginId = null;
            String loginPassword = null;

            while (rs.next()) {
                loginId = rs.getString("loginid");
                loginPassword = rs.getString("loginpass");
            }

            IndexEntity indexEntity = new IndexEntity(loginId, loginPassword);

            indexEntity.setLoginId(loginId);
            indexEntity.setLoginPassword(loginPassword);

            indexEntityList.add(indexEntity.getLoginId());
            indexEntityList.add(indexEntity.getLoginPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeDB(conn);
        }

        return indexEntityList;
    }

    public List<String> getSessionInfo(String pLoginId, String pLoginPassword) {

        List<String> returnList = new ArrayList<>();
        String loginId = "";
        String loginPassword = "";
        String loginName = "";
        boolean kanriFlg = false;

        Connection conn = null;

        try {
            // JDBCドライバ読み込み
            super.loadJDBCDriver();

            // DBへ接続
            conn = super.connectionDB(conn);

            // SELECT文を準備
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT M.id,L.loginpass,M.name,L.kanriflg");
            sql.append(" FROM meibo AS M JOIN login AS L M.id = L.id");
            sql.append(" WHERE loginid = ?;");

            PreparedStatement pStmt = conn.prepareStatement(sql.toString());
            pStmt.setString(1, pLoginId);

            // SELECTを実行し、結果表を取得
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                loginId = rs.getString("loginid");
                loginPassword = rs.getString("loginpass");
                loginName = rs.getString("name");
                kanriFlg = rs.getBoolean("kanriFlg");
            }

            SessionKanriBean sessionKanriBean = new SessionKanriBean(loginId, loginPassword, loginName, kanriFlg);

            sessionKanriBean.setLoginId(loginId);
            sessionKanriBean.setLoginPassword(loginPassword);
            sessionKanriBean.setLoginName(loginName);
            sessionKanriBean.setKanriFlg(kanriFlg);

            returnList.add(sessionKanriBean.getLoginId());
            returnList.add(sessionKanriBean.getLoginPassword());
            returnList.add(sessionKanriBean.getLoginName());
            returnList.add(String.valueOf(sessionKanriBean.getKanriFlg()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeDB(conn);
        }

        return returnList;
    }
}
