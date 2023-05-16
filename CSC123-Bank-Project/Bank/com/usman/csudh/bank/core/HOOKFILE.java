package com.usman.csudh.bank.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HOOKFILE extends Abstracts {

	public InputStream getItems() throws Exception{
		return new FileInputStream(new File("exchange-rate.csv"));
	}
}
