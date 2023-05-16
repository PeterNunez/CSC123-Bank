package com.usman.csudh.bank.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HookHttp extends Abstracts {

	public InputStream getItems() throws Exception {
		
		HttpRequest.Builder item = HttpRequest.newBuilder();
		item.uri(URI.create("http://www.usman.cloud/banking/exchange-rate.csv"));
		
		HttpRequest a = item.build();
		HttpClient person = HttpClient.newHttpClient();
		HttpResponse<String> response = person.send(a, HttpResponse.BodyHandlers.ofString());
		
		byte[] open = response.body().getBytes();
		//return new open;
		return new ByteArrayInputStream(open);
	}
}
