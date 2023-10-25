package control;

import java.util.ArrayList;
import java.util.List;

import control.common.DAOCommon;

public class TestBL {
	
	public Object testBLMain() {
		
		Object result = null;
		String str1 = null;
		str1 = "userall.sql";
		List<String> column = new ArrayList<String>();
		column.add("id");
		column.add("name");
		List<Object> statement = new ArrayList<Object>();
		statement.add(1);
		statement.add("");
		
		DAOCommon dbCommon = new DAOCommon();
		
		result = dbCommon.selectSQL(str1, column,statement);
		
		return result;
		
	}
	
}
