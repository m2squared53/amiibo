package com.m2squared.amiibo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmiiboUtils {
	
	private AmiiboUtils() {};
	
	public static String byteArrayToHexString(byte[] bytes) {
		  String result = "";
		  for (byte b : bytes) {
		    result += byteToHexString(b);
		  }
		  return result;
	}
	
	public static String byteToHexString(byte b) {
		return Integer.toString((b & 0xff) + 0x100, 16).substring(1).toUpperCase();
	}
	
	public static String byteToNormalizedHexString(byte b) {
		return "0x" + byteToHexString(b);
	}
	
	public static String castedByteToHexString(byte b) {
		return "(byte) " + byteToNormalizedHexString(b);
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
	
	public static void main(String[] args) {
		byte b = (byte) 183;
		byte[] ba = {(byte) 7, (byte) 199, (byte) 255, (byte) 16, (byte) 46};
		System.out.println(byteToHexString(b));
		System.out.println(byteToNormalizedHexString(b));
		System.out.println(castedByteToHexString(b));
		System.out.println(byteArrayToHexString(ba));
	}

}
