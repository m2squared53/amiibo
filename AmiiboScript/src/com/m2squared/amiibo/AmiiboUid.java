package com.m2squared.amiibo;

import java.math.BigInteger;

public class AmiiboUid {
	private String uid;
	private String BCC0;
	private String BCC1;
	private String pw;
	
	public AmiiboUid(String uid) {
		if (!isValidUid(uid)) {
			StringBuilder err = new StringBuilder(uid).append(" is not a valid Amiibo UID.");
			throw new IllegalArgumentException(err.toString());
		}
		this.init(uid);
	}
	
	private void init(String uid) {
		uid = uid.trim().toUpperCase();
		int length = uid.length();
		if (length == 18) {
			this.uid = uid.substring(0, 16);
			this.BCC0 = uid.substring(6, 8);
			this.BCC1 = uid.substring(16);
		}
		else {
			String[] tagBytes = getTagBytes(uid);
			int bcc0 = (0x88 ^ 
					Integer.parseUnsignedInt(tagBytes[0], 16) ^ 
					Integer.parseUnsignedInt(tagBytes[1], 16) ^
					Integer.parseUnsignedInt(tagBytes[2], 16));
			int bcc1 = (Integer.parseUnsignedInt(tagBytes[3], 16) ^ 
					Integer.parseUnsignedInt(tagBytes[4], 16) ^
					Integer.parseUnsignedInt(tagBytes[5], 16) ^
					Integer.parseUnsignedInt(tagBytes[6], 16));
			this.BCC0 = Integer.toHexString(bcc0).toUpperCase();
			this.BCC1 = Integer.toHexString(bcc1).toUpperCase();
			this.uid = tagBytes[0] + tagBytes[1] + tagBytes[2] + this.BCC0 +
					tagBytes[3] + tagBytes[4] + tagBytes[5] + tagBytes[6];
		}
		String[] tagBytes = getTagBytes(this.uid); 
		int pw1 = (0xAA ^ Integer.parseUnsignedInt(tagBytes[1], 16) ^ Integer.parseUnsignedInt(tagBytes[4], 16));
		int pw2 = (0x55 ^ Integer.parseUnsignedInt(tagBytes[2], 16) ^ Integer.parseUnsignedInt(tagBytes[5], 16));
		int pw3 = (0xAA ^ Integer.parseUnsignedInt(tagBytes[4], 16) ^ Integer.parseUnsignedInt(tagBytes[6], 16));
		int pw4 = (0x55 ^ Integer.parseUnsignedInt(tagBytes[5], 16) ^ Integer.parseUnsignedInt(tagBytes[7], 16));
		this.pw = Integer.toHexString(pw1).toUpperCase() + Integer.toHexString(pw2).toUpperCase() +
				Integer.toHexString(pw3).toUpperCase() + Integer.toHexString(pw4).toUpperCase();
	}
	
	public static String[] getTagBytes(String uid) {
		if (!isValidUid(uid)) {
			StringBuilder err = new StringBuilder(uid).append(" is not a valid Amiibo UID.");
			throw new IllegalArgumentException(err.toString());
		}
		String[] tagBytes = new String[uid.length() / 2];
		for (int i = 0; i < uid.length(); i += 2) {
			tagBytes[i / 2] = uid.substring(i, i + 2);
		}
		return tagBytes;
	}
	
	public static boolean isValidUid(String uid) {
		boolean valid = true;
		int length = uid.length();
		if (uid.startsWith("04")) {
			if (length == 14 || length == 18) {
				try {
					new BigInteger(uid, 16);
				}	
				catch (NumberFormatException e) {
					valid = false;
				}
			}
		}
		return valid;
	}
	
	public String getUid() {
		return uid;
	}

	public String getBCC0() {
		return BCC0;
	}

	public String getBCC1() {
		return BCC1;
	}

	@Override
	public String toString() {
		return "AmiiboBin [uid=" + uid + ", BCC0=" + BCC0 + ", BCC1=" + BCC1+ ", pw=" + pw
				+ "]";
	}

	public static void main(String[] args) {
		AmiiboUid large = new AmiiboUid("040348C72AE33E8176");
		AmiiboUid small = new AmiiboUid("0403482AE33E81");
		AmiiboUid wolf = new AmiiboUid("0491A4B992754C812A");
		System.out.println("Large - 040348C72AE33E8176");
		System.out.println(large);
		System.out.println("Small - 0403482AE33E81");
//
		System.out.println(small);
		
		int pw1 = (0xAA ^ 0x03 ^ 0x2A);
		int pw2 = (0x55 ^ 0x48 ^ 0xE3);
		int pw3 = (0xAA ^ 0x2A ^ 0x3E);
		int pw4 = (0x55 ^ 0xE3 ^ 0x81);

		String pw = Integer.toHexString(pw1).toUpperCase() + Integer.toHexString(pw2).toUpperCase() +
		Integer.toHexString(pw3).toUpperCase() + Integer.toHexString(pw4).toUpperCase();
		System.out.println();
		System.out.println("PW: " + pw);
		
		System.out.println();
		System.out.println();
		System.out.println("Wolf - 0491A4B992754C812A");
		System.out.println(wolf);

		pw1 = (0xAA ^ 0x91 ^ 0x92);
		pw2 = (0x55 ^ 0xA4 ^ 0x75);
		pw3 = (0xAA ^ 0x92 ^ 0x4C);
		pw4 = (0x55 ^ 0x75 ^ 0x81);
		pw = Integer.toHexString(pw1).toUpperCase() + Integer.toHexString(pw2).toUpperCase() +
		Integer.toHexString(pw3).toUpperCase() + Integer.toHexString(pw4).toUpperCase();
		System.out.println();
		System.out.println("Wolf PW: " + pw);
	}
}
