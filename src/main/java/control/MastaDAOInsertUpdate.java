package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.MastaEntity;

public class MastaDAOInsertUpdate extends DBConnection{
	
	public void InsertUser(MastaEntity pEntity) {

		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SQL文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO user(id,name,kanriflg) VALUES(?,?,?)");
			
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, pEntity.getUserid());
			pStmt.setString(2, pEntity.getName());
			pStmt.setBoolean(3, pEntity.getKanriFlg());

			//Insertを実行　
			pStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
	}
	
	public void InsertLogin(MastaEntity pEntity) {

		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SQL文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO login(id,loginid,password) VALUES(?,?,?);");
			
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, pEntity.getUserid());
			pStmt.setString(2, pEntity.getLoginid());
			pStmt.setString(3, pEntity.getLoginpassword());

			//Insertを実行　
			pStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
	}
	
	public void delUser(MastaEntity pEntity) {

		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SQL文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE user SET del = '1' where id = ?;");
			
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, pEntity.getUserid());

			//Insertを実行　
			pStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
	}
	
	public void delLogin(MastaEntity pEntity) {

		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SQL文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE login SET del = '1' where id = ?;");
			
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, pEntity.getUserid());

			//Insertを実行　
			pStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
	}
	
}
