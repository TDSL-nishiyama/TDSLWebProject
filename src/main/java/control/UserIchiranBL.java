package control;

import java.util.ArrayList;
import java.util.List;

import model.UserAddEntity;

public class UserIchiranBL {
	//実行結果をサーブレットに戻す
	public List<UserAddEntity> resultUserList(UserIchiranBL userIchiranBL) {

		List<UserAddEntity> result = new ArrayList<>();
		
		//DAOクラスのインスタンス化
		MastaDAOSelect mastaDAOsel = new MastaDAOSelect();
		result = mastaDAOsel.getUserIchiran(mastaDAOsel);

		return result;
	}
}
