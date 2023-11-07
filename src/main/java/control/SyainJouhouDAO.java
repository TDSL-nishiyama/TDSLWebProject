package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	 * @param statement ステートメント（不要な場合、NULLを設定）
	 * @param kanriFlg 管理者フラグ
	 * @return selectの結果
	 */
	public List<SyainJouhouEntity> selectSQL(String fileName, List<String> column, List<Object> statement, boolean kanriFlg) {

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
				//ステートメントの設定
				if(statement != null) {
					int cnt = statement.size(); //ステートメントを設定する数
					for(int i = 0 ; i < cnt; i++) {
						pStmt.setObject(i+1, statement.get(i));
					}
				}

				//クエリの実行
				ResultSet rs = pStmt.executeQuery();

				int id = 0;
				String sei = null;
				String mei = null;
				Date nyuusyaYMD = null;
				String syusshin = null;

				//格納
				while (rs.next()) {
					id = rs.getInt(column.get(0));
					sei = rs.getString(column.get(1));
					mei = rs.getString(column.get(2));
					nyuusyaYMD = rs.getDate(column.get(3));
					syusshin = rs.getString(column.get(4));
					SyainJouhouEntity entity = new SyainJouhouEntity(id,sei, mei,nyuusyaYMD,syusshin);
					result.add(entity);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				//発行
				PreparedStatement pStmt = conn.prepareStatement(sql.toString());
				//ステートメントの設定
				if(statement != null) {
					int cnt = statement.size(); //ステートメントを設定する数
					for(int i = 0 ; i < cnt; i++) {
						pStmt.setObject(i+1, statement.get(i));
					}
				}
				
				//クエリの実行
				ResultSet rs = pStmt.executeQuery();

				int id = 0;
				String sei = null;
				String mei = null;
				Date nyuusyaYMD = null;
				String syusshin = null;
				
				//格納
				while (rs.next()) {
					id = rs.getInt(column.get(0));
					sei = rs.getString(column.get(1));
					mei = rs.getString(column.get(2));
					nyuusyaYMD = rs.getDate(column.get(3));
					syusshin = rs.getString(column.get(4));
					SyainJouhouEntity entity = new SyainJouhouEntity(id,sei, mei,nyuusyaYMD,syusshin);
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
