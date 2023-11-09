package control;

import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;
import control.common.DBAccess;
import model.MastaEntity;

public class MastaDAOInsertUpdate extends DAOCommon implements DBAccess {

  public void InsertUser(MastaEntity pEntity) {

    List<Object> statement = new ArrayList<>();

    statement.add(pEntity.getUserid());
    statement.add(pEntity.getName());
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

  public void InsertUserShoisai(MastaEntity pEntity) {

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

}
