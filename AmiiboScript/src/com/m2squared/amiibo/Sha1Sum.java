package com.m2squared.amiibo;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;

public class Sha1Sum {
	
	public static String sha1Sum(byte[] bytes) throws GeneralSecurityException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return byteArrayToHexString(md.digest(bytes));
	}
	
	public static String byteArrayToHexString(byte[] bytes) {
		  String result = "";
		  for (byte b : bytes) {
		    result += byteToHexString(b);
		  }
		  return result;
	}
	
	public static String byteToHexString(byte b) {
		return Integer.toString((b & 0xff) + 0x100, 16).substring(1);
	}
	
	public static byte[] getBytesFromFile(String path) throws IOException {
		FileInputStream fin = new FileInputStream(path);
		int in = -1;
		List<Byte> bin = new ArrayList<Byte>();
		while ((in = fin.read()) != -1) {
			bin.add((byte) in);
		}
		fin.close();
		byte[] bytes = new byte[bin.size()];
		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = bin.get(i);
		}
		return bytes;
	}

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		System.out.println(sha1Sum(getBytesFromFile("C:\\cygwin64\\home\\m2squared\\Amiibo\\retailKey.bin")));
		System.out.println(byteToHexString((byte) (0x88 ^ 0x04 ^ 0x03 ^ 0x48)));
	}
}
