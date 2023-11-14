package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaEntity;
import model.SyainJouhouEntity;

public class MastaDAOInsertUpdate extends DAOCommon implements DBAccess {

  public void InsertUser(MastaEntity pEntity) {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());
    statement.add(pEntity.getUserName());
    statement.add(pEntity.getKanriFlg());

    super.executeDML("insertUser.sql", statement);

  }

  public void InsertLogin(MastaEntity pEntity) {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());
    statement.add(pEntity.getLoginid());
    statement.add(pEntity.getLoginpassword());

    super.executeDML("insertLogin.sql", statement);

  }

  public void InsertUserShoisai(SyainJouhouEntity pEntity) {
    List<Object> statement = new ArrayList<>();
    
    statement.add(pEntity.getId());
    statement.add(pEntity.getSei());
    statement.add(pEntity.getSei_yomi());
    statement.add(pEntity.getMei());
    statement.add(pEntity.getMei_yomi());
    statement.add(pEntity.getNyuusyaYMD());
    statement.add(pEntity.getTaisytaYMD());
    statement.add(pEntity.getSeibetsu());
    statement.add(pEntity.getSeinenngappi());
    statement.add(pEntity.getSyusshin());
    statement.add(pEntity.getJuusyo());
    
    super.executeDML("insertUserShousai.sql", statement);
  }

  public void delUser(MastaEntity pEntity) {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());

    super.executeDML("delUser.sql", statement);
  }

  public void delLogin(MastaEntity pEntity) {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());

    super.executeDML("delLogin.sql", statement);

  }

  public void updUser(Map<String, String> updKoumoku) {
    List<Object> statement = new ArrayList<>();

    statement.add(updKoumoku.get("username"));
    statement.add(updKoumoku.get("kanriflg"));
    statement.add(updKoumoku.get("userIdUpd"));

    super.executeDML("updUser.sql", statement);
  }

}
