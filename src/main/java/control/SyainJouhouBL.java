package control;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import model.SyainJouhouBean;
import model.SyainJouhouEntity;
import control.common.CalcCommon;

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
      for (int i = 0; i < resultDB.size(); i++) {
        //名前の設定
        StringBuilder name = new StringBuilder();
        name.append(resultDB.get(i).getSei());
        name.append(resultDB.get(i).getMei());

        SyainJouhouBean bean = new SyainJouhouBean(resultDB.get(i).getId(), name.toString(),
            resultDB.get(i).getNyuusyaYMD(), resultDB.get(i).getSyusshin());

        result.add(bean);
      }

    //管理者の場合
    } else {
      //取得カラム名の設定
      column.add("id");
      column.add("sei");
      column.add("mei");
      column.add("nyuusyaYMD");
      column.add("syusshin");
      column.add("seibetsu");
      column.add("seinenngappi");

      resultDB = dao.selectSQL("usershousai_kanrisya.sql", column, null, kanriFlg);

      //DBから取得した情報をもとに必要事項を設定
      CalcCommon calc = new CalcCommon();
      for (int i = 0; i < resultDB.size(); i++) {
        //名前の設定
        StringBuilder name = new StringBuilder();
        name.append(resultDB.get(i).getSei());
        name.append(resultDB.get(i).getMei());

        //入社年次の設定(DateをStringからLocalDateに変換して差分を取得)
        Period prNyuusyaYMD = null;
        prNyuusyaYMD = calc.diffDate(resultDB.get(i).getNyuusyaYMD());
        StringBuilder nenji = new StringBuilder();
        nenji.append(prNyuusyaYMD.getYears());
        nenji.append("年");
        nenji.append(prNyuusyaYMD.getMonths());
        nenji.append("ヵ月");

        //性別の設定
        String seibetsu = null;
        switch (resultDB.get(i).getSeibetsu()) {
        case "0":
          seibetsu = "男";
          break;
        case "1":
          seibetsu = "女";
          break;
        case "2":
          seibetsu = "その他";
          break;
        default:
          seibetsu = "その他";
        }

        //年齢の設定
        Period prSeinenngappi = null;
        prSeinenngappi = calc.diffDate(resultDB.get(i).getSeinenngappi());
        StringBuilder age = new StringBuilder();
        age.append(prSeinenngappi.getYears());
        age.append("才");

        SyainJouhouBean bean = new SyainJouhouBean(resultDB.get(i).getId(), name.toString(),
            resultDB.get(i).getNyuusyaYMD(), nenji.toString(), resultDB.get(i).getSyusshin(), seibetsu,
            age.toString());

        result.add(bean);
      }
    }

    return result;
  }
}
