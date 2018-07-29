package com.schooldeal.util;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.schooldeal.exception.BusiException;

public class EncryptionUtil {
	private EncryptionUtil(){}
	private static final String ALGORITHM = "DES";

	/**
	 * Get Des Key
	 */
	public static byte[] getKey() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance(ALGORITHM);
		SecretKey deskey = keygen.generateKey();
		return deskey.getEncoded();
		
	}

	/**
	 * Encrypt Messages
	 * @throws BusiException 
	 */
	public static byte[] encode(byte[] input, byte[] key) throws BusiException  {
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM);
		byte[] str = null;
		try {
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			str = c1.doFinal(input);
		}catch (Exception e) {
			throw new BusiException("encode Error",e.getMessage());
		}
		return str;
	}

	/**
	 * Decrypt Messages
	 */
	public static byte[] decode(byte[] input, byte[] key) throws BusiException{
		SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM);
		byte[] str = null;
		try {
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			str = c1.doFinal(input);
		}catch (Exception e) {
			throw new BusiException("encode Error",e.getMessage());
		}
		
		return str;
	}

	/**
	 * MD5
	 */
	public static byte[] md5(byte[] input) throws BusiException {
		java.security.MessageDigest alg;
		byte[] str = null;
		try {
			alg = java.security.MessageDigest
					.getInstance("MD5");
			alg.update(input);
			str = alg.digest();
		} catch (Exception e) {
			throw new BusiException("encode Error",e.getMessage());
		} // or "SHA-1"
		
		return str;
	}

	/**
	 * Convert byte[] to String
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp;
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	/**
	 * Convert String to byte[]
	 */
	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + Character.toString(arr[i++]) + Character.toString(arr[i]);
			Integer byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = byteint.byteValue();
		}
		return b;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(byte2hex(encode("OL".getBytes(),Constants.DBKEYEES.getBytes())));
//		System.out.println(byte2hex(encode("nK%17p*-GfTd".getBytes(),Constants.DBKEYEES.getBytes())));
//		String ipaddrsss = "jdbc:mysql://192.168.156.105:20031/ddglcore?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&allowMultiQueries=true&useOldAliasMetadataBehavior=true";
//		System.out.println(byte2hex(encode(ipaddrsss.getBytes(),Constants.DBKEYEES.getBytes())));
//
//
//		System.out.println(byte2hex(encode("1qaz!QAZ".getBytes(),"0af5b157".getBytes())));
		//String rtnStr=byte2hex(encode("jdbc:mysql://192.168.156.105:20031/ddglcore?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&allowMultiQueries=true&useOldAliasMetadataBehavior=true".getBytes(),
//				Constants.DBKEYEES.getBytes()));
		//String srcStr=new String(decode(hex2byte("rO0ABXciABAnMB/BnGa2wSYFeoNVXUG4vaTpjK0hxCaO34P2OQGILQ=="), Constants.DBKEYEES.getBytes()));
		//System.out.println("---="+srcStr);
		
	}
}
