package application;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import org.apache.commons.codec.digest.DigestUtils;

public class CertParser 

{
  public static void main(String[] argv) throws Exception {
    FileInputStream is = new FileInputStream("C:\\Users\\thiyagu\\Desktop\\debug.jks");

    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
    keystore.load(is, "android".toCharArray());

    String alias = "androiddebugkey";

    Key key = keystore.getKey(alias, "android".toCharArray());
    if (key instanceof PrivateKey) {
      // Get certificate of public key
      Certificate cert = keystore.getCertificate(alias);
     


System.out.println(DigestUtils.sha1Hex(cert.getEncoded()));
System.out.println(DigestUtils.sha256Hex(cert.getEncoded()));


    }
  }
}