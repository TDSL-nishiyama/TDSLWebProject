package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import control.common.CastCommon;
import model.KoutsuuBean;
import model.KoutsuuEntity;

public class KoutsuuBL {

  /**
   * {@index 交通費精算確認画面に表示される情報の取得を行う}
   * @param selId　対象者の社員ID（ログインしているユーザー）
   * @return
   * @throws SQLException
   */
  public List<KoutsuuBean> getKoutsuuKakunin(int selId) throws SQLException {
    List<KoutsuuBean> result = new ArrayList<KoutsuuBean>();
    List<KoutsuuEntity> resultDB = new ArrayList<KoutsuuEntity>();

    KoutsuuDAO dao = new KoutsuuDAO();
    resultDB = dao.selectKoutsuuKakunin(selId);

    CastCommon castCommon = new CastCommon();

    for (int i = 0; i < resultDB.size(); i++) {
      KoutsuuBean bean = new KoutsuuBean(resultDB.get(i).getNo(), resultDB.get(i).getId(),
          resultDB.get(i).getUserName(),
          resultDB.get(i).getSendmailaddress(), castCommon.chgLDTtoStr(resultDB.get(i).getRiyouhiduke()),
          resultDB.get(i).getKukan_start(), resultDB.get(i).getKukan_end(), resultDB.get(i).getKingaku(),
          resultDB.get(i).getBikou(),
          chgStatus(resultDB.get(i).getSeisannStatus()), castCommon.chgLDTtoStr(resultDB.get(i).getYoukyuuJikoku()));
      result.add(bean);
    }

    return result;
  }

  /**
   * ステータスコード変換用
   * @param status 0→申請中 1→差戻中 2→承認済 3→振込完了
   * @return 変換結果
   */
  private String chgStatus(String status) {
    String result = null;

    switch (status) {
    case "0":
      result = "申請中";
      break;
    case "1":
      result = "差戻中";
      break;
    case "2":
      result = "承認済";
      break;
    case "3":
      result = "振込完了";
      break;
    }

    return result;
  }
}
