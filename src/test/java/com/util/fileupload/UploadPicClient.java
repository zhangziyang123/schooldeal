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
		//1,创建客户端socket
		Socket s = new Socket("localhost",10088);
		//2,读取客户端要上传的图片文件
		FileInputStream fis = new FileInputStream("E:\\ceshi\\yaoshuige.png");
		//3,获取Socket输出流，将读到的图片的数据发送到服务端
		OutputStream out = s.getOutputStream();
		
		byte[] buf = new byte[1021];
		int len =0;
		while((len=fis.read(buf))!=-1){
			out.write(buf,0,len);
		}
		
		//告诉服务端说：这边的数据发送完毕让服务端停止读取
		s.shutdownOutput();
		
		//读取服务端发回的内容
		
		InputStream in = s.getInputStream();
		
		byte[] bufIn = new byte[1024];
		
		int lenIn = in.read(buf);
		String text = new String (buf,0,lenIn);
		System.out.println(text);
		
		//关闭资源
		fis.close();
		s.close();
	}

}
