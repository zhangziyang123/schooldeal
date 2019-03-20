package com.util.fileupload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UploadPicClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		//1,�����ͻ���socket
		Socket s = new Socket("localhost",10088);
		//2,��ȡ�ͻ���Ҫ�ϴ���ͼƬ�ļ�
		FileInputStream fis = new FileInputStream("E:\\ceshi\\yaoshuige.png");
		//3,��ȡSocket���������������ͼƬ�����ݷ��͵������
		OutputStream out = s.getOutputStream();
		
		byte[] buf = new byte[1021];
		int len =0;
		while((len=fis.read(buf))!=-1){
			out.write(buf,0,len);
		}
		
		//���߷����˵����ߵ����ݷ�������÷����ֹͣ��ȡ
		s.shutdownOutput();
		
		//��ȡ����˷��ص�����
		
		InputStream in = s.getInputStream();
		
		byte[] bufIn = new byte[1024];
		
		int lenIn = in.read(buf);
		String text = new String (buf,0,lenIn);
		System.out.println(text);
		
		//�ر���Դ
		fis.close();
		s.close();
	}

}
