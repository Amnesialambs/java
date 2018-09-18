package com.yzy.test;

import java.io.IOException;

import com.yzy.util.FileUpLoadClient;

public class FileUploadTest {
	
	
	public static void main(String[] args) {
		
		String ip = "127.0.0.1";
		Integer port = 10086;
		String filePath = "F:\\repository.zip";
		try {
			FileUpLoadClient client = new FileUpLoadClient(ip,port);
			client.statusInfo();
			client.sendFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
