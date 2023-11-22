package control;

import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;

public class TestBL extends DAOCommon{
  
  public Object testBLMain() {
    
    Object result = null;
    String str1 = null;
    str1 = "test.sql";
    List<String> column = new ArrayList<String>();
    column.add("id");
    column.add("name");
    List<Object> statement = new ArrayList<Object>();
    statement.add(1);
    statement.add("");
    
    result = super.selectSQL(str1, column,statement);
    
    return result;
    
  }
  
}
