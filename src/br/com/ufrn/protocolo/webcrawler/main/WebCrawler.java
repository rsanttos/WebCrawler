package br.com.ufrn.protocolo.webcrawler.main;

import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

	private String urlInicial;
	private String palavraChave;
	private PaginaHTML paginaInicial;
	private List<String> linksGeral;
	private int profundidade;

	public WebCrawler(String urlInicial, String palavraChave) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = palavraChave;
		this.paginaInicial = new PaginaHTML(urlInicial, palavraChave);
		this.linksGeral = new ArrayList<String>();
		this.profundidade = -1;
	}

	public WebCrawler(String urlInicial, String palavraChave, int profundidade) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = palavraChave;
		this.paginaInicial = new PaginaHTML(urlInicial, palavraChave);
		this.linksGeral = new ArrayList<String>();
		this.profundidade = profundidade;
	}

	public WebCrawler(String urlInicial) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = "";
		this.paginaInicial = new PaginaHTML(urlInicial);
	}

	public void executa() {
		System.out.println("***************************************************");
		System.out.println("-> Acessando página: " + paginaInicial.getUrl());
		System.out.println("***************************************************");
		List<String> links = paginaInicial.getLinks();

		if (profundidade == -1) {
			profundidade = links.size();
		}

		for (int i = 0; i < profundidade; i++) {
			if (!linksGeral.contains(links.get(i))) {
				linksGeral.add(links.get(i));
				paginaInicial = new PaginaHTML(links.get(i), palavraChave);
				executa();
			} else {
				System.out.println("Link visitado.");
			}
		}
	}
}
