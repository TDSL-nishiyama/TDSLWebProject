package control;

import java.util.ArrayList;
import java.util.List;

import model.SyainJouhouEntity;

public class SyainJouhouBL {

	private boolean kanriFlg;

	public SyainJouhouBL(boolean pKanriFlg) {
		//コンストラクタでメンバに管理者フラグを設定
		kanriFlg = pKanriFlg;
	}

	//実行結果をサーブレットに戻す
	public List<SyainJouhouEntity> resultSyainList(SyainJouhouBL syainJouhouBL) {

		List<SyainJouhouEntity> result = new ArrayList<>();
		List<String> column = new ArrayList<String>();
		
		//DAOクラスのインスタンス化
		SyainJouhouDAO dao = new SyainJouhouDAO();
		
		//管理者ではない場合
		if (kanriFlg == false) {
			//取得カラム名の設定
			column.add("sei");
			column.add("mei");
			
			//ステートメントの設定
			result = dao.selectSQL("usershousai_ippann.sql", column,kanriFlg);
			
		//管理者の場合
		} else {
			//取得カラム名の設定
			column.add("id");
			column.add("sei");
			column.add("mei");
			
			result = dao.selectSQL("usershousai_kanrisya.sql", column,kanriFlg);

		}

		return result;
	}

}
