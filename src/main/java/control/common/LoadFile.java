package control.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import constents.Const.Common;

public class LoadFile {
	/**
	 *{@index} SQL文取得用
	 *@param String loadPath 発行したいSQLのファイルが格納されているパス 
	 *@throws IOException 
	 **/
	public String getSQLFile(String loadPath) throws IOException {

		String sql = null;

		BufferedReader bfReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(loadPath), Charset.forName(Common.ENCODE_UTF8)));

		sql = bfReader.readLine();

		bfReader.close();
		return sql;
	}

	/**
	 *{@index} プロパティファイル取得用（単要素）　
	 *@param String loadPath プロパティファイルが格納されているパス 
	 *@param String pName 取得したい要素名
	 **/
	public String getProItem(String loadPath, String pName) {

		Properties properties = new Properties();
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(loadPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(pName);
	}

	/**
	 *{@index} プロパティファイル取得用（複数要素）　
	 *@param String loadPath プロパティファイルが格納されているパス 
	 *@param String[] pName 取得したい要素名
	 **/
	public Map<String, String> getProItem(String loadPath, String[] pName) {

		Properties properties = new Properties();
		FileInputStream inStream = null;
		Map<String, String> result = new HashMap<String, String>();

		try {
			inStream = new FileInputStream(loadPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			properties.load(inStream);
			for (int i = 0; pName.length > i; i++) {
				result.put(pName[i], properties.getProperty(pName[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
