package com.m2squared.amiibo;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.io.IOException;

import static com.m2squared.amiibo.AmiiboUtils.*;

public class Sha1Sum {
	
	public static String sha1Sum(byte[] bytes) throws GeneralSecurityException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return byteArrayToHexString(md.digest(bytes));
	}
	
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		System.out.println(sha1Sum(getBytesFromFile("C:\\cygwin64\\home\\m2squared\\Amiibo\\retailKey.bin")));
	}
}
