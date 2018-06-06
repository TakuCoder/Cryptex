package application;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypto 


{

   static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile)
   
   {
	 try {
	       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
	       Cipher cipher = Cipher.getInstance("AES");
	       cipher.init(cipherMode, secretKey);

	       FileInputStream inputStream = new FileInputStream(inputFile);
	       byte[] inputBytes = new byte[(int) inputFile.length()];
	       inputStream.read(inputBytes);

	       byte[] outputBytes = cipher.doFinal(inputBytes);

	       FileOutputStream outputStream = new FileOutputStream(outputFile);
	       outputStream.write(outputBytes);

	       inputStream.close();
	       outputStream.close();

	    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
                     | InvalidKeyException | BadPaddingException
	             | IllegalBlockSizeException | IOException e) {
		e.printStackTrace();
            }
     }
	
     public static void main(String[] args) 
     
     {
//	String key = "3EE6FFA63A13FDDD";
//	File inputFile = new File("C:\\Users\\thiyagu\\Desktop\\text.txt");
//	File encryptedFile = new File("C:\\Users\\thiyagu\\Desktop\\text.encrypted");
//	//File decryptedFile = new File("C:\\Users\\thiyagu\\Desktop\\decrypted-text.txt");
//		
//	try {
//	     Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
//	    // Crypto.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
//	     System.out.println("Success");
//	 } catch (Exception ex) {
//	     System.out.println(ex.getMessage());
//             ex.printStackTrace();
//	 }
     }
	
}

