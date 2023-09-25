package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import model.SyainJouhouEntity;

public class SyainJouhouDAO extends DBConnection {

	public List<SyainJouhouEntity> findAll(SyainJouhouDAO SyainJouhouDAO) {

		List<SyainJouhouEntity> syainJouhouList = new ArrayList<SyainJouhouEntity>();

		NumberFormat nfNum = NumberFormat.getNumberInstance();

		Connection conn = null;

		try {
			//JDBCドライバを読み込み
			super.loadJDBCDriver();

			//DBへ接続
			conn = super.connectionDB(conn);

			//SELECT文を準備
			StringBuilder sql = new StringBuilder();

			sql.append(
					"SELECT m.id,m.name,m.yomigana,m.seibetsu,j.syussin,j.juusyo,j.teate,t.job,t.salary,t.nyuusya,t.kinzoku");
			sql.append(" FROM meibo m");
			sql.append(" INNER JOIN juusyo j");
			sql.append(" ON m.id = j.id");
			sql.append(" INNER JOIN taiguu t");
			sql.append(" ON t.id = j.id;");

			PreparedStatement pStmt = conn.prepareStatement(sql.toString());

			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//結果表に格納されたレコードの内容をSyainJouhouEntityに設定
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String yomigana = rs.getString("yomigana");
				String seibetsu = rs.getString("seibetsu");
				String syussinn = rs.getString("syussin");
				String juusyo = rs.getString("juusyo");
				int jutakuTeateUmu = rs.getInt("teate");
				String shokui = rs.getString("job");
				String salary = nfNum.format(rs.getInt("salary")); //カンマ区切りで設定
				int nyuusyaNengetsu = rs.getInt("nyuusya");
				int kinzokuNennsuu = rs.getInt("kinzoku");

				SyainJouhouEntity SyainJouhouEntityAdd = new SyainJouhouEntity(id, name, yomigana, seibetsu,
						syussinn, juusyo, jutakuTeateUmu, shokui, salary, nyuusyaNengetsu, kinzokuNennsuu);
				syainJouhouList.add(SyainJouhouEntityAdd);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			//データベース切断
			if (conn != null) {
				super.closeDB(conn);
			}
		}
		return syainJouhouList;

	}
}
