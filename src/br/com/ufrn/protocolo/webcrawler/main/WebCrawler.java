package br.com.ufrn.protocolo.webcrawler.main;

import java.util.List;

public class WebCrawler {

	private String urlInicial;
	private String palavraChave;
	private PaginaHTML paginaInicial;
	public WebCrawler(String urlInicial, String palavraChave) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = palavraChave;
		this.paginaInicial = new PaginaHTML(urlInicial);
	}
	public WebCrawler(String urlInicial) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = "";
		this.paginaInicial = new PaginaHTML(urlInicial);
	}
	
	public void executa() {
		List<String> links = paginaInicial.getLinks();
		for(String link : links) {
			new PaginaHTML(link);
		}
	}
}
