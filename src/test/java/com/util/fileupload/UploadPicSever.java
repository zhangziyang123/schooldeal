package com.util.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadPicSever {

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	//����tcp��socket�����
	ServerSocket ss = new ServerSocket(10088);
	//��ȡ�ͻ���
	Socket s = ss.accept();
	String ip = s.getInetAddress().getHostAddress();
	System.out.println(ip+".....connected");
	//��ȡ�ͻ��˷���������
	InputStream in = s.getInputStream();
	//����ȡ�������ݴ洢��һ���ļ��С�
	File dir = new File("E:\\ceshi\\day2019.1.17");
	if(!dir.exists()){
		dir.mkdirs();
	}
	File file = new File(dir,"blue.jpg");
	FileOutputStream fos = new FileOutputStream(file);
	byte[] buf = new byte[1024];
	int len = 0;
	while ((len=in.read(buf))!=-1){
		fos.write(buf,0,len);
	}
	
	//��ȡsocket����������ϴ��ɹ��������͸��ͻ���
	OutputStream out = s.getOutputStream();
	out.write("�ϴ��ɹ�".getBytes());
	
	fos.close();
	s.close();
	ss.close();
}
}