package control.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class CalcCommon {
	
	/**
	 *{@index} 日付計算（年月日）
	 *@param Date date 日付 
	 *@return Period パラメータに設定した日付-現在日付
	 **/
	public Period diffDate(Date date) {

		Period result = null;

		String strDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
		String[] splYMD = strDate.split("/");
		LocalDate ldate = LocalDate.of(Integer.parseInt(splYMD[0]), Integer.parseInt(splYMD[1]),
				Integer.parseInt(splYMD[2]));
		result = Period.between(ldate, LocalDate.now());

		return result;

	}

}
