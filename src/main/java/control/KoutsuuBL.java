package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KoutsuuBean;
import model.KoutsuuEntity;

public class KoutsuuBL {

  public List<KoutsuuBean> getKoutsuuKakunin(int selId) throws SQLException {
    List<KoutsuuBean> result = new ArrayList<KoutsuuBean>();
    List<KoutsuuEntity> resultDB = new ArrayList<KoutsuuEntity>();

    KoutsuuDAO dao = new KoutsuuDAO();
    resultDB = dao.selectKoutsuuKakunin(selId);

    for (int i = 0; i < resultDB.size(); i++) {
      KoutsuuBean bean = new KoutsuuBean(resultDB.get(i).getNo(), resultDB.get(i).getId(), resultDB.get(i).getUserName(),
          resultDB.get(i).getSendmailaddress(), resultDB.get(i).getKukan_start(),
          resultDB.get(i).getKukan_end(), resultDB.get(i).getKingaku(), resultDB.get(i).getBikou(),
          chgStatus(resultDB.get(i).getSeisannStatus()));
      result.add(bean);
    }

    return result;

  }
  
  /**
   * ステータスコード変換用
   * @param str 0→申請中 1→差戻中 2→承認済 3→振込完了
   * @return 変換結果
   */
  private String chgStatus(String str) {
    String result = null;
    
    switch(str){
    case "0":
      result = "申請中";
    case "1":
      result = "差戻中";
    case "2":
      result = "承認済";
    case "3":
      result = "振込完了";
    }
    
    return result;
  }
}
