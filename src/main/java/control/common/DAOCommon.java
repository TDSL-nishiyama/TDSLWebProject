package control.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constents.Const.Common;

public class DAOCommon implements DBAccess {

	//SQLファイルの格納フォルダを指定
	private String sqlPath = Common.SQL_FILE_PATH;

	/**
	 * {@index} SELECT COUNT 実行メソッド
	 * @param fileName 実行したいSQLファイルの名前
	 * @return select count の結果(int)
	 */
	public int countSQL(String fileName) {

		int result = 0;
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
		try {
			//発行
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pStmt.executeQuery();

			//格納
			while (rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//DB切断
		DBAccess.super.closeDB(conn);

		return result;
	}

	/**
	 * {@index} SELECT実行メソッド　（1レコード）
	 * @param fileName 実行したいSQLファイルの名前
	 * @param column 取得したいカラム名
	 * @return selectの結果(List)
	 */
	public List<Object> selectSQL(String fileName, List<String> column) {

		List<Object> result = new ArrayList<Object>();
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
		try {
			//発行
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			//クエリの実行
			ResultSet rs = pStmt.executeQuery();

			int listCnt = column.size();

			//格納
			while (rs.next()) {
				for (int i = 0; i < listCnt; i++) {
					result.add(rs.getObject(column.get(i)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//DB切断
		DBAccess.super.closeDB(conn);

		return result;
	}

	/**
	 * {@index} SELECT実行メソッド ?あり　（1レコード）
	 * @param fileName 実行したいSQLファイルの名前
	 * @param column 取得したいカラム名　List<String>
	 * @param statement PreparedStatmentの内容　List<Object>
	 * @return selectの結果(List)
	 */
	public List<Object> selectSQL(String fileName, List<String> column, List<Object> statement) {

		List<Object> result = new ArrayList<Object>();
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
		try {
			//発行
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			int colCnt = column.size();
			int steCnt = statement.size();

			//PreparedStatementの設定
			for (int i = 0; i < steCnt; i++) {
				pStmt.setObject(i+1, statement.get(i));
			}
			
			//クエリの実行
			ResultSet rs = pStmt.executeQuery();

			//格納
			while (rs.next()) {
				for (int i = 0; i < colCnt; i++) {
					result.add(rs.getObject(column.get(i)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//DB切断
		DBAccess.super.closeDB(conn);

		return result;
	}
	
	/**
	 * {@index} INSERT・UPDATE・DLETE実行メソッド（1レコード）
	 * @param fileName 実行したいSQLファイルの名前
	 * @param column 取得したいカラム名　List<String>
	 * @param statement PreparedStatmentの内容　List<Object>　ない場合はNULLを指定してください
	 * @return selectの結果(List)
	 */
	public void executeDML(String fileName, List<String> column, List<Object> statement) {

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
		try {
			//発行
			PreparedStatement pStmt = conn.prepareStatement(sql.toString());
			if(!(statement.equals(null))) {
				int cnt = statement.size(); //ステートメントを設定する数
				for(int i = 0 ; i < cnt; i++) {
					pStmt.setObject(i+1, statement.get(i));
				}
			}
			//クエリの実行
			pStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//DB切断
		DBAccess.super.closeDB(conn);

	}

	//SQL文作成
	protected String makeSQL(String sqlPath) {
		String result = null;

		LoadFile loadfile = new LoadFile();
		try {
			result = loadfile.getSQLFile(sqlPath);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
