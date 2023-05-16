package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Abstracts  {

	
	public abstract InputStream getItems() throws Exception;
	public ArrayList<String> Curr() throws Exception {
		
		InputStream d = getItems();
		BufferedReader bf = new  BufferedReader(new InputStreamReader(d));
		
		ArrayList<String> something = new ArrayList<String>();
		String line = null;
		while((line = bf.readLine())!= null) {
			something.add(line);
		}
		return something;
	}
	
	public static Abstracts getValue(String a)throws Exception {
		String b = "file";
		String c = "webservice";
		
		if(a.equalsIgnoreCase("file")) {
			return new HOOKFILE();
		}else if(a.equalsIgnoreCase("webservice")) {
			return new HookHttp();
		}else if(!a.matches(b)|!a.matches(c)) {
			throw new Exception("Please try again");
		}
	}
}
