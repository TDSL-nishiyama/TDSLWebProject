package control.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constents.Const.Common;

public class DAOCommon implements DBAccess {

	//select count(countColumn)用
	public int countSQL(String fileName) {

		int result = 0;
		String sqlPath = null;
		sqlPath = Common.SQL_FILE_PATH + fileName;

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
			while(rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//DB切断
		DBAccess.super. closeDB(conn);
		
		return result;
	}

	//SQL文作成
	private String makeSQL(String sqlPath) {
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
