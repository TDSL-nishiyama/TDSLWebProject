package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.Common;
import control.common.DAOCommon;
import control.common.DBAccess;
import model.SyainJouhouEntity;

public class SyainJouhouDAO extends DAOCommon implements DBAccess {

	private String sqlPath = Common.SQL_FILE_PATH;

	/**
	 * {@index} 社員情報の情報を取得
	 * @param fileName 実行したいSQLファイルの名前
	 * @param column 取得したいカラム名
	 * @param kanriFlg 管理者フラグ
	 * @return selectの結果
	 */
	public List<SyainJouhouEntity> selectSQL(String fileName, List<String> column, boolean kanriFlg) {

		List<SyainJouhouEntity> result = new ArrayList<SyainJouhouEntity>();
		sqlPath += fileName;

		//JDBC接続
		DBAccess.super.loadJDBCDriver();

		//DB接続
		Connection conn = null;
		conn = DBAccess.super.connectionDB(conn);

		//SQL文の作成
		String sql = null;
		sql = makeSQL(sqlPath);

		//クエリの発行・格納
		if (kanriFlg == false) {
			try {
				//発行
				PreparedStatement pStmt = conn.prepareStatement(sql.toString());
				//クエリの実行
				ResultSet rs = pStmt.executeQuery();

				String sei = null;
				String mei = null;

				//格納
				while (rs.next()) {
					sei = rs.getString(column.get(0));
					mei = rs.getString(column.get(1));
					SyainJouhouEntity entity = new SyainJouhouEntity(sei, mei);
					result.add(entity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				//発行
				PreparedStatement pStmt = conn.prepareStatement(sql.toString());
				//クエリの実行
				ResultSet rs = pStmt.executeQuery();

				int id = 0;
				String sei = null;
				String mei = null;

				//格納
				while (rs.next()) {
					id = rs.getInt(column.get(0));
					sei = rs.getString(column.get(1));
					mei = rs.getString(column.get(2));
					SyainJouhouEntity entity = new SyainJouhouEntity(id, sei, mei);
					result.add(entity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//DB切断
		DBAccess.super.closeDB(conn);

		return result;
	}

}
