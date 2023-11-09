package control;

import java.util.ArrayList;
import java.util.List;

import model.MastaEntity;

public class UserSelectBL {
  //実行結果をサーブレットに戻す
  public List<MastaEntity> resultSyainList(SyainJouhouBL syainJouhouBL) {

    List<MastaEntity> result = new ArrayList<>();

    //DAOクラスのインスタンス化
    MastaDAOSelect mastaSel = new MastaDAOSelect();
    mastaSel.getUserIchiran(mastaSel);

    return result;
  }
}
