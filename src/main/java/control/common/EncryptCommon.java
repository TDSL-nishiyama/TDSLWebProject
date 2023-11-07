package control.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import constents.Const.Common;

public class EncryptCommon {

	public String cryptoHash(String password) throws NoSuchAlgorithmException {

		String result = "";

		//パスワードのハッシュ化
		MessageDigest digest = MessageDigest.getInstance(Common.HASH_MODE);
		byte[] b = digest.digest(password.getBytes());
		for (int i = 0; i < b.length; i++) {
			result += b[i];
		}

		return result;
	}

}
