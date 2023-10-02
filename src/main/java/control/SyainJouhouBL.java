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

		//DAOクラスのインスタンス化
		SyainJouhouDAO syainJouhouDAO = new SyainJouhouDAO();

		//管理者ではない場合
		if (kanriFlg == false) {
			result = syainJouhouDAO.findIppan(syainJouhouDAO);
			//管理者の場合
		} else {
			result = syainJouhouDAO.findAll(syainJouhouDAO);
		}

		return result;
	}

}
