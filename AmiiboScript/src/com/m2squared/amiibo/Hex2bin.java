package com.m2squared.amiibo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.m2squared.amiibo.AmiiboConstants.*;

public class Hex2bin {

	private ByteBuffer bin;
	
	public Hex2bin() {
		this.bin = ByteBuffer.allocateDirect(NTAG215_SIZE);
	}
			
	public Hex2bin(int capacity) {
		this.bin = ByteBuffer.allocateDirect(capacity);
	}
	
	public void insert(int offset, byte b) {
		this.bin.put(offset, b);
	}
	
	public void insert(String offset, byte b) {
		this.insert(offset, 16, b);
	}
	
	public void insert(String offset, int radix, byte b) {
		this.insert(Integer.parseUnsignedInt(offset, radix), b);
	}
	
	public byte[] toByteArray() {
		byte[] current = this.bin.array();
		return Arrays.copyOf(current, current.length);
	}
	
	public int getCapacity() {
		return this.bin.capacity();
	}
	
	public File writeToFile(String path) throws IOException {
		File filePath = new File(path);
		FileOutputStream out = null;
		out = new FileOutputStream(filePath);
		out.write(this.bin.array());
		out.close();
		return filePath;
	}

}
