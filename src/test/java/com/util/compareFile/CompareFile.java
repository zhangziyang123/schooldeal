package com.util.compareFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CompareFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			InputStream in=new FileInputStream(new File("C://Users//admin//Desktop//m1.png"));
			InputStream in2=new FileInputStream(new File("C://Users//admin//Desktop//m.png"));
			System.out.println(in.available()/1024);
			System.out.println(in2.available()/1024);
			int a;
			int b;
			while((a=in.read())!=-1&&(b=in2.read())!=-1){
				System.out.println(a);
				System.out.println(b);
				if(a!=b){
					System.out.println("文件不同");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
