package com.hjcrm.publics.util;

import java.security.MessageDigest;

/**
 * MD5技术加密解密
 * @author likang
 * @date 2016-10-11 下午5:14:12
 */
public class MD5Tools {

	/**
	 * 
	 * @param src
	 * @return
	 * @author likang 
	 * @date 2016-10-11 下午5:22:44
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 解析
	 * @param hexString
	 * @return
	 * @author likang 
	 * @date 2016-10-11 下午5:15:46
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * 将指定byte数组以16进制的形式打印到控制台
	 * @param b
	 * @author likang 
	 * @date 2016-10-11 下午5:15:57
	 */
	public static void printHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}

	}

	/**
	 * Convert char to byte
	 * @param c char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789abcdef".indexOf(c);
	}

	/**
	 * 加密
	 * @param str
	 * @return
	 * @author likang 
	 * @date 2016-10-11 下午5:16:12
	 */
	public static String encode(String str) {
		String strDigest = "";
		try {
			// 此 MessageDigest 类为应用程序提供信息摘要算法的功能，必须用try,catch捕获
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] data;
			data = md5.digest(str.getBytes("utf-8"));// 转换为MD5码
			strDigest = bytesToHexString(data);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return strDigest;
	}

}
