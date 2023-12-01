package control;

import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import constents.UserShousai;
import model.SyainJouhouBean;
import model.SyainJouhouEntity;
import control.common.CalcCommon;

public class SyainJouhouBL {

  private boolean kanriFlg;

  //コンストラクタでメンバに管理者フラグを設定
  public SyainJouhouBL(boolean pKanriFlg) {
    kanriFlg = pKanriFlg;
  }

  /**
   * {@index} 社員情報閲覧画面に表示する情報の結果表(一覧)を取得してSyainJouhouBeanに格納するクラス
   * @param syainJouhouBL
   * @return
   */
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
      column.add(UserShousai.COL_ID);
      column.add(UserShousai.COL_SEI);
      column.add(UserShousai.COL_MEI);
      column.add(UserShousai.COL_NYUUSYAYMD);
      column.add(UserShousai.COL_SYUSSHIN);

      resultDB = dao.selectSQL("usershousai.sql", column, null, kanriFlg);
      for (int i = 0; i < resultDB.size(); i++) {
        //名前の設定
        String name = null;
        name = setName(resultDB.get(i).getSei(), resultDB.get(i).getMei());

        SyainJouhouBean bean = new SyainJouhouBean(resultDB.get(i).getId(), name.toString(),
            resultDB.get(i).getNyuusyaYMD(), resultDB.get(i).getSyusshin());

        result.add(bean);
      }

      //管理者の場合
    } else {
      //取得カラム名の設定
      column.add(UserShousai.COL_ID);
      column.add(UserShousai.COL_SEI);
      column.add(UserShousai.COL_SEI_YOMI);
      column.add(UserShousai.COL_MEI);
      column.add(UserShousai.COL_MEI_YOMI);
      column.add(UserShousai.COL_NYUUSYAYMD);
      column.add(UserShousai.COL_TAISYAYMD);
      column.add(UserShousai.COL_SEIBETSU);
      column.add(UserShousai.COL_SEINENGAPPI);
      column.add(UserShousai.COL_SYUSSHIN);
      column.add(UserShousai.COL_JYUUSYO);

      resultDB = dao.selectSQL("usershousai.sql", column, null, kanriFlg);

      //DBから取得した情報をもとに必要事項を設定
      for (int i = 0; i < resultDB.size(); i++) {
        //名前の設定
        String name = null;
        name = setName(resultDB.get(i).getSei(), resultDB.get(i).getMei());

        //入社年次の設定
        String nenji = null;
        //入社日付は必須項目ではないためNULLチェックを行う
        if (resultDB.get(i).getNyuusyaYMD() != null) {
          nenji = setNenji(resultDB.get(i).getNyuusyaYMD());
        } else {
          nenji = "";
        }

        //性別の設定
        String seibetsu = null;
        seibetsu = setSeibetsu(resultDB.get(i).getSeibetsu());

        //年齢の設定
        String age = null;
        age = setAge(resultDB.get(i).getSeinenngappi());

        SyainJouhouBean bean = new SyainJouhouBean(resultDB.get(i).getId(), name,
            resultDB.get(i).getNyuusyaYMD(), nenji, resultDB.get(i).getSyusshin(), resultDB.get(i).getJuusyo(),
            seibetsu, age);

        result.add(bean);
      }
    }

    return result;
  }

  /**
   * {@index} 社員情報編集を行うユーザーの結果表（1レコード）を取得してSyainJouhouBeanに格納する
   * @param pUpdId　編集するユーザーID
   * @return
   */
  public List<SyainJouhouBean> resultSyainJouhouHensyu(int pUpdId) {

    List<SyainJouhouEntity> resultDB = new ArrayList<>();
    List<SyainJouhouBean> result = new ArrayList<>();
    List<String> column = new ArrayList<String>();
    List<Object> statement = new ArrayList<Object>();

    //DAOクラスのインスタンス化
    SyainJouhouDAO dao = new SyainJouhouDAO();

    //取得カラム名の設定
    column.add(UserShousai.COL_ID);
    column.add(UserShousai.COL_SEI);
    column.add(UserShousai.COL_SEI_YOMI);
    column.add(UserShousai.COL_MEI);
    column.add(UserShousai.COL_MEI_YOMI);
    column.add(UserShousai.COL_NYUUSYAYMD);
    column.add(UserShousai.COL_TAISYAYMD);
    column.add(UserShousai.COL_SEIBETSU);
    column.add(UserShousai.COL_SEINENGAPPI);
    column.add(UserShousai.COL_SYUSSHIN);
    column.add(UserShousai.COL_JYUUSYO);

    statement.add(pUpdId);

    resultDB = dao.selectSQL("usershousaihensyuu.sql", column, statement, kanriFlg);

    //DBから取得した情報をもとに必要事項を設定
    for (int i = 0; i < resultDB.size(); i++) {
      //名前の設定
      String name = null;
      name = setName(resultDB.get(i).getSei(), resultDB.get(i).getMei());

      //入社年次の設定
      String nenji = null;
      //入社日付は必須項目ではないためNULLチェックを行う
      if (resultDB.get(i).getNyuusyaYMD() != null) {
        nenji = setNenji(resultDB.get(i).getNyuusyaYMD());
      } else {
        nenji = "";
      }

      //性別の設定
      String seibetsu = null;
      seibetsu = setSeibetsu(resultDB.get(i).getSeibetsu());

      //年齢の設定
      String age = null;
      age = setAge(resultDB.get(i).getSeinenngappi());

      SyainJouhouBean bean = new SyainJouhouBean(resultDB.get(i).getId(),
          resultDB.get(i).getSei(), resultDB.get(i).getSei_yomi(), resultDB.get(i).getMei(),
          resultDB.get(i).getMei_yomi(), name,
          resultDB.get(i).getNyuusyaYMD(), null, seibetsu, resultDB.get(i).getSeinenngappi(),
          age, nenji, resultDB.get(i).getSyusshin(), resultDB.get(i).getJuusyo());
      result.add(bean);
    }

    return result;

  }

  /**
   * {@index} 社員情報更新を行う
   * @param updKoumoku　アップデートする項目
   * @param pUpdId　編集するユーザーID
   * @return
   * @throws SQLException 
   */
  public void syainJouhouUpd(Map<String, Object> updKoumoku, int pUpdId) throws SQLException {

    SyainJouhouDAO dao = new SyainJouhouDAO();
    List<Object> statement = new ArrayList<>();
    //TODO 将来的に一般ユーザーでも特定の項目を編集できるようにするためSQLファイル名は呼び出し側で指定
    String fileName = "updUserShousai.sql";

    /*実行クエリ
     * UPDATE usershousai SET sei=?,sei_yomi=?,mei=?,mei_yomi=?,nyuusyaYMD=?,seibetsu=?
     * ,seinenngappi=?,syusshin=?,juusyo=? where id = ?;
    */
    //SET句
    statement.add(updKoumoku.get(UserShousai.COL_SEI));
    statement.add(updKoumoku.get(UserShousai.COL_SEI_YOMI));
    statement.add(updKoumoku.get(UserShousai.COL_MEI));
    statement.add(updKoumoku.get(UserShousai.COL_MEI_YOMI));
    statement.add(updKoumoku.get(UserShousai.COL_NYUUSYAYMD));
    statement.add(updKoumoku.get(UserShousai.COL_SEIBETSU));
    statement.add(updKoumoku.get(UserShousai.COL_SEINENGAPPI));
    statement.add(updKoumoku.get(UserShousai.COL_SYUSSHIN));
    statement.add(updKoumoku.get(UserShousai.COL_JYUUSYO));
    //WHERE句
    statement.add(pUpdId);

    dao.updateSyainJouhou(fileName, statement);

  }

  private String setName(String sei, String mei) {
    String result = null;

    StringBuilder name = new StringBuilder();
    name.append(sei);
    name.append(mei);

    result = name.toString();

    return result;
  }

  private String setNenji(Date nyuusyaYMD) {

    String result = null;
    Period prNyuusyaYMD = null;
    CalcCommon calc = new CalcCommon();
    prNyuusyaYMD = calc.diffDate(nyuusyaYMD);
    StringBuilder nenji = new StringBuilder();
    nenji.append(prNyuusyaYMD.getYears());
    nenji.append("年");
    nenji.append(prNyuusyaYMD.getMonths());
    nenji.append("ヵ月");

    result = nenji.toString();

    return result;
  }

  private String setSeibetsu(String seibetsu) {

    String result = null;
    switch (seibetsu) {
    case "0":
      result = "男";
      break;
    case "1":
      result = "女";
      break;
    case "2":
      result = "その他";
      break;
    default:
      result = "その他";
    }

    return result;
  }

  private String setAge(Date seinenngappi) {

    String result = null;

    Period prSeinenngappi = null;
    CalcCommon calc = new CalcCommon();
    prSeinenngappi = calc.diffDate(seinenngappi);
    StringBuilder age = new StringBuilder();
    age.append(prSeinenngappi.getYears());
    age.append("才");

    result = age.toString();

    return result;
  }

}
