package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MastaEntity;

public class MastaDAOSelect extends DBConnection {
	
	public String checkUserId(MastaEntity mastaEntity) {
		
		String result = "";
		Connection conn = null;

		try {

			// JDBCドライバを読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT COUNT(id) FROM user WHERE id = ? AND del = '';");
			
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, mastaEntity.getUserid());
			
			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			super.closeDB(conn);
		}
		
		return result;
	}

	public List<MastaEntity> getUserIchiran(MastaDAOSelect MastaDAOSelect) {

		List<MastaEntity> returnList = new ArrayList<>();
		int id = 0;
		String loginId = "";
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

			sql.append("SELECT U.id,L.loginid,U.name,U.kanriFlg");
			sql.append(" FROM user AS U INNER JOIN login AS L");
			sql.append(" ON U.id = L.id");
			sql.append(" WHERE U.del = '';");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容をMastaEntityに設定
			while (rs.next()) {
				id = rs.getInt("id");
				loginId = rs.getString("loginid");
				loginName = rs.getString("name");
				kanriFlg = rs.getBoolean("kanriFlg");

				MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId);
				returnList.add(mastaEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(conn);
		}

		return returnList;
	}
	
	public List<MastaEntity> getUserIchiranAll(MastaDAOSelect MastaDAOSelect) {

		List<MastaEntity> returnList = new ArrayList<>();
		int id = 0;
		String loginId = "";
		String loginName = "";
		boolean kanriFlg = false;
		String sakujo = "";

		Connection conn = null;

		try {
			// JDBCドライバ読み込み
			super.loadJDBCDriver();

			// DBへ接続
			conn = super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT U.id,L.loginid,U.name,U.kanriFlg,U.del");
			sql.append(" FROM user AS U INNER JOIN login AS L");
			sql.append(" ON U.id = L.id;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容をMastaEntityに設定
			while (rs.next()) {
				id = rs.getInt("id");
				loginId = rs.getString("loginid");
				loginName = rs.getString("name");
				kanriFlg = rs.getBoolean("kanriFlg");
				sakujo = rs.getString("del");

				MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId,"", sakujo);
				returnList.add(mastaEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(conn);
		}

		return returnList;
	}
	
	public List<MastaEntity> getUserIchiran(MastaDAOSelect MastaDAOSelect,int pID) {

		List<MastaEntity> returnList = new ArrayList<>();
		int id = 0;
		String loginId = "";
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

			sql.append("SELECT U.id,L.loginid,U.name,U.kanriFlg");
			sql.append(" FROM user AS U INNER JOIN login AS L");
			sql.append(" ON U.id = L.id");
			sql.append(" WHERE U.del = '' AND id = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, pID);
			
			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容をMastaEntityに設定
			while (rs.next()) {
				id = rs.getInt("id");
				loginId = rs.getString("loginid");
				loginName = rs.getString("name");
				kanriFlg = rs.getBoolean("kanriFlg");

				MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId);
				returnList.add(mastaEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(conn);
		}

		return returnList;
	}
}
