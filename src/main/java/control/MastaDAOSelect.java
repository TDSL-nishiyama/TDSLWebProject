package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constents.Const.Common;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaEntity;

public class MastaDAOSelect extends DAOCommon implements DBAccess {

	private String sqlPath = Common.SQL_FILE_PATH;

	public String checkUserId(MastaEntity mastaEntity) {

		String result = "";
		List<Object> statement = new ArrayList<>();
		statement.add(String.valueOf(mastaEntity.getUserid()));
		result = String.valueOf(super.countSQL("checkUserId.sql", statement));
		
		return result;
	}

	public List<MastaEntity> getUserIchiran(MastaDAOSelect MastaDAOSelect,String fileName) {

		List<MastaEntity> returnList = new ArrayList<>();
		int id = 0;
		String loginId = "";
		String loginName = "";
		boolean kanriFlg = false;

		Connection conn = null;

		try {
			// JDBCドライバ読み込み
			DBAccess.super.loadJDBCDriver();

			// DBへ接続
			conn = DBAccess.super.connectionDB(conn);

			//SQL文の作成
			sqlPath += fileName;
			String sql = null;
			sql = makeSQL(sqlPath);

			PreparedStatement pStmt = conn.prepareStatement(sql);

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
			DBAccess.super.closeDB(conn);
		}
		
		sqlPath = Common.SQL_FILE_PATH;

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
			DBAccess.super.loadJDBCDriver();

			// DBへ接続
			conn = DBAccess.super.connectionDB(conn);

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

				MastaEntity mastaEntity = new MastaEntity(id, loginName, kanriFlg, loginId, "", sakujo);
				returnList.add(mastaEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBAccess.super.closeDB(conn);
		}

		return returnList;
	}

	public List<MastaEntity> getHensyuUser(MastaDAOSelect MastaDAOSelect, Map<String, String> updKoumoku) {

		List<MastaEntity> returnList = new ArrayList<>();
		int id = 0;
		String loginId = "";
		String loginName = "";
		boolean kanriFlg = false;

		Connection conn = null;

		try {
			// JDBCドライバ読み込み
			DBAccess.super.loadJDBCDriver();

			// DBへ接続
			conn = DBAccess.super.connectionDB(conn);

			// SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT U.id,L.loginid,U.name,U.kanriFlg");
			sql.append(" FROM user AS U INNER JOIN login AS L");
			sql.append(" ON U.id = L.id");
			sql.append(" WHERE U.del = '' AND U.id = ?;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			pStmt.setInt(1, Integer.parseInt(updKoumoku.get("userIdUpd")));

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
			DBAccess.super.closeDB(conn);
		}

		return returnList;
	}
}
