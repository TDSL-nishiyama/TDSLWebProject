package control.common;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
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

  //暗号化
  public byte[] encrypto(String plainText, PublicKey publickey) throws GeneralSecurityException {

    // 書式:"アルゴリズム/ブロックモード/パディング方式"
    Cipher encrypter = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    encrypter.init(Cipher.ENCRYPT_MODE, publickey);

    return encrypter.doFinal(plainText.getBytes());
  }

  //複合化
  public String decrypto(byte[] cryptoText, PrivateKey privatekey) throws GeneralSecurityException {

    Cipher decrypter = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    decrypter.init(Cipher.DECRYPT_MODE, privatekey);

    return new String(decrypter.doFinal(cryptoText));
  }

  //暗号鍵の作成
  public KeyPair generateKeyPair() throws NoSuchAlgorithmException {

    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2024);

    return keyGen.generateKeyPair();
  }

}
