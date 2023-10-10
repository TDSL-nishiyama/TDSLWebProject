package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.IndexEntity;
import model.SessionKanriBean;

public class LoginDAO extends DBConnection {
	
	public String findLoginIdtoId(String pLoginId) {

		Connection conn = null;
		String userId = null;
		

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT id");
			sql.append(" FROM login");
			sql.append(" WHERE loginid = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setString(1, pLoginId);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				userId = rs.getString("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}

		return userId;
	}
	
	//IDの最大値取得用
	public String maxUserId() {
		
		Connection conn = null;
		String resultCount = null;
		
		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT MAX(ID) FROM user;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				resultCount = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
		return resultCount;
	}
	
	//loginidの重複チェック用
	public String countLoginId(String pLoginId) {
		
		Connection conn = null;
		String resultCount = null;
		
		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("select count(loginid) from login where loginid = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setString(1, pLoginId);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				resultCount = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
		return resultCount;
	}

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

			sql.append("SELECT loginid,password");
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
			String ppassword) {

		Connection conn = null;
		List<String> indexEntityList = new ArrayList<>();

		try {
			// JDBCドライバ読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT loginid,password");
			sql.append(" FROM login");
			sql.append(" WHERE loginid = ? AND password = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setString(1, pLoginId);
			pStmt.setString(2, ppassword);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			String loginId = null;
			String password = null;

			while (rs.next()) {
				loginId = rs.getString("loginid");
				password = rs.getString("password");
			}

			IndexEntity indexEntity = new IndexEntity(loginId, password);

			indexEntity.setLoginId(loginId);
			indexEntity.setpassword(password);

			indexEntityList.add(indexEntity.getLoginId());
			indexEntityList.add(indexEntity.getpassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(conn);
		}

		return indexEntityList;
	}

	public List<String> getSessionInfo(String pLoginId, String pPassword) {

		List<String> returnList = new ArrayList<>();
		String loginId = "";
		String password = "";
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

			sql.append("SELECT L.loginid,L.password,U.name,U.kanriFlg");
			sql.append(" FROM user AS U INNER JOIN login AS L");
			sql.append(" ON U.id = L.id");
			sql.append(" WHERE loginid = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setString(1, pLoginId);

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				loginId = rs.getString("loginid");
				password = rs.getString("password");
				loginName = rs.getString("name");
				kanriFlg = rs.getBoolean("kanriFlg");
			}

			SessionKanriBean sessionKanriBean = 
					new SessionKanriBean(loginId, password, loginName, kanriFlg);

			sessionKanriBean.setLoginId(loginId);
			sessionKanriBean.setpassword(password);
			sessionKanriBean.setLoginName(loginName);
			sessionKanriBean.setKanriFlg(kanriFlg);

			returnList.add(sessionKanriBean.getLoginId());
			returnList.add(sessionKanriBean.getpassword());
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
