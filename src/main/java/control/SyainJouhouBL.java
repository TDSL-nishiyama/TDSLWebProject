package control;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import model.SyainJouhouBean;
import model.SyainJouhouEntity;

public class SyainJouhouBL {

	private boolean kanriFlg;

	public SyainJouhouBL(boolean pKanriFlg) {
		//コンストラクタでメンバに管理者フラグを設定
		kanriFlg = pKanriFlg;
	}

	//実行結果をサーブレットに戻す
	public List<SyainJouhouBean> resultSyainJouhou(SyainJouhouBL syainJouhouBL) {

		List<SyainJouhouEntity> resultDB = new ArrayList<>();
		List<SyainJouhouBean> result = new ArrayList<>();
		List<String> column = new ArrayList<String>();

		//DAOクラスのインスタンス化
		SyainJouhouDAO dao = new SyainJouhouDAO();

		//管理者ではない場合
		if (kanriFlg == false) {
			//取得カラム名の設定
			column.add("id");
			column.add("sei");
			column.add("mei");
			column.add("nyuusyaYMD");
			column.add("syusshin");

			resultDB = dao.selectSQL("usershousai_ippann.sql", column, null, kanriFlg);

			//管理者の場合
		} else {
			//取得カラム名の設定
			column.add("id");
			column.add("sei");
			column.add("mei");
			column.add("nyuusyaYMD");
			column.add("syusshin");

			resultDB = dao.selectSQL("usershousai_kanrisya.sql", column, null, kanriFlg);

		}
		
		//DBから取得した情報をもとに必要事項を設定		
		for(int i = 0; i < resultDB.size(); i++) {
			//名前の設定
			StringBuilder name = new StringBuilder();
			name.append(resultDB.get(i).getSei());
			name.append(resultDB.get(i).getMei());
			
			//入社年次の設定(DateをStringからLocalDateに変換して差分を取得)
			String strnyuusyaYMD = new SimpleDateFormat("yyyy/MM/dd").format((resultDB.get(i).getNyuusyaYMD()));
			String[] splYMD = strnyuusyaYMD.split("/");
			LocalDate ldate = LocalDate.of(Integer.parseInt(splYMD[0]),Integer.parseInt(splYMD[1]),Integer.parseInt(splYMD[2]));
			Period nyuusyaYMD = Period.between(ldate, LocalDate.now());
			StringBuilder nenji = new StringBuilder();
			nenji.append(nyuusyaYMD.getYears());
			nenji.append("年");
			nenji.append(nyuusyaYMD.getMonths());
			nenji.append("ヵ月");
			
			SyainJouhouBean bean = 
					new SyainJouhouBean(resultDB.get(i).getId(),name.toString(),
					resultDB.get(i).getNyuusyaYMD(),nenji.toString(),resultDB.get(i).getSyusshin());
			
			result.add(bean);
		}
		
		return result;
	}

	//実行結果をサーブレットに戻す
	public List<SyainJouhouEntity> resultSyainHensyu(SyainJouhouBL syainJouhouBL,String pStatement) {

		List<SyainJouhouEntity> result = new ArrayList<>();
		List<String> column = new ArrayList<String>();

		//DAOクラスのインスタンス化
		SyainJouhouDAO dao = new SyainJouhouDAO();

		//管理者ではない場合
		if (kanriFlg == false) {
			//取得カラム名の設定
			column.add("id");
			column.add("sei");
			column.add("mei");
			column.add("nyuusyaYMD");
			column.add("syusshin");

			//ステートメントの設定
			List<Object> statement = new ArrayList<Object>();
			statement.add(Integer.parseInt(pStatement));

			//クエリの実行
			result = dao.selectSQL("usershousai_ippann.sql", column, statement, kanriFlg);

		//管理者の場合
		} else {
			//取得カラム名の設定
			column.add("id");
			column.add("sei");
			column.add("mei");
			column.add("nyuusyaYMD");
			column.add("syusshin");

			result = dao.selectSQL("usershousai_kanrisya.sql", column, null, kanriFlg);

		}

		return result;
	}

}
