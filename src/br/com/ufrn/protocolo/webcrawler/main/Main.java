package br.com.ufrn.protocolo.webcrawler.main;

public class Main {

	
	public static void main(String[] args) {		
		WebCrawler wc = new WebCrawler("http://www.tribunadonorte.com.br/");
		wc.executa();
	}
	
}
