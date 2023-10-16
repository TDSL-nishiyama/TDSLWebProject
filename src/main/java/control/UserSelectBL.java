package control;

import java.util.ArrayList;
import java.util.List;

import model.UserAddEntity;

public class UserSelectBL {
	//実行結果をサーブレットに戻す
	public List<UserAddEntity> resultSyainList(SyainJouhouBL syainJouhouBL) {

		List<UserAddEntity> result = new ArrayList<>();

		//DAOクラスのインスタンス化
		MastaDAOSelect mastaSel = new MastaDAOSelect();
		mastaSel.getUserIchiran(mastaSel);

		return result;
	}
}
