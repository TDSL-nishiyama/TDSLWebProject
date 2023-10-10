package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserAddEntity;

public class MastaDAOSelect extends DBConnection {

	public List<UserAddEntity> getUserIchiran(MastaDAOSelect MastaDAOSelect) {

		List<UserAddEntity> returnList = new ArrayList<>();
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
			sql.append(" ON U.id = L.id;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			// SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容をUserAddEntityに設定
			while (rs.next()) {
				id = rs.getInt("id");
				loginId = rs.getString("loginid");
				loginName = rs.getString("name");
				kanriFlg = rs.getBoolean("kanriFlg");

				UserAddEntity userAddEntity = new UserAddEntity(id, loginName, kanriFlg, loginId);
				returnList.add(userAddEntity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(conn);
		}

		return returnList;
	}
}
