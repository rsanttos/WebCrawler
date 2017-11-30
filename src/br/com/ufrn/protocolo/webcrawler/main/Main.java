package br.com.ufrn.protocolo.webcrawler.main;

public class Main {

	
	public static void main(String[] args) {		
		WebCrawler wc = new WebCrawler("http://g1.globo.com/", "economia", 5);
		wc.executa();
	}
	
}
