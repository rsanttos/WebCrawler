package br.com.ufrn.protocolo.webcrawler.main;

import java.util.ArrayList;
import java.util.List;

public class WebCrawler {

	private String urlInicial;
	private String palavraChave;
	private PaginaHTML pagina;
	private List<String> linksGeral;
	private int profundidade;
	private int profundidadeAtual;
	private boolean possuiPalavraChave;
	private boolean possuiProfundidade;

	public WebCrawler(String urlInicial, String palavraChave) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = palavraChave;
		this.pagina = new PaginaHTML(urlInicial, palavraChave);
		this.linksGeral = new ArrayList<String>();
		this.profundidade = -1;
		this.profundidadeAtual = 0;
		this.possuiPalavraChave = true;
		this.possuiProfundidade = false;
	}

	public WebCrawler(String urlInicial, String palavraChave, int profundidade) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = palavraChave;
		this.pagina = new PaginaHTML(urlInicial, palavraChave);
		this.linksGeral = new ArrayList<String>();
		this.profundidade = profundidade;
		this.profundidadeAtual = 0;
		this.possuiPalavraChave = true;
		this.possuiProfundidade = true;
	}

	public WebCrawler(String urlInicial) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = "";
		this.profundidade = -1;
		this.profundidadeAtual = 0;
		this.linksGeral = new ArrayList<String>();
		this.pagina = new PaginaHTML(urlInicial);
		this.possuiPalavraChave = false;
		this.possuiProfundidade = false;
	}

	public WebCrawler(String urlInicial, int profundidade) {
		super();
		this.urlInicial = urlInicial;
		this.palavraChave = "";
		this.profundidade = profundidade;
		this.profundidadeAtual = 0;
		this.linksGeral = new ArrayList<String>();
		this.pagina = new PaginaHTML(urlInicial);
		this.possuiPalavraChave = false;
		this.possuiProfundidade = true;
	}

	public void executa() {
		List<String> linksAux = new ArrayList<String>();
		if (possuiProfundidade) {
			for (int i = 0; i < profundidade; i++) {
				processaLinks(pagina, linksAux);
			}
		} else {
			while (pagina.getLinks().size() > 0) {
				processaLinks(pagina, linksAux);
			}
		}
		pagina.setLinks(linksAux);
		profundidadeAtual++;
		executa();
	}
	
	public void processaLinks(PaginaHTML pagina, List<String> linksAux) {
		for (String link : pagina.getLinks()) {
			if (possuiPalavraChave) {
				if (link.contains(palavraChave) && !linksGeral.contains(link)) {
					linksGeral.add(link);
					acessaLink(link, linksAux);
				}
			} else {
				if (!linksGeral.contains(link)) {
					linksGeral.add(link);
					acessaLink(link, linksAux);
				}
			}
		}
	}

	public void acessaLink(String link, List<String> linksAux) {
		System.out.println("********************************************");
		System.out.println("--> ACESSANDO LINK: " + link);
		System.out.println("--> PROFUNDIDADE: " + profundidadeAtual);
		System.out.println("********************************************");
		linksAux = new PaginaHTML(link).getLinks();
		for (String l : linksAux) {
			if (!linksGeral.contains(l)) {
				if (possuiPalavraChave && l.contains(palavraChave)) {
					System.out.println(l);
					linksGeral.add(l);
				} else if (!possuiPalavraChave) {
					System.out.println(l);
					linksGeral.add(l);
				}
			}
		}
	}
	// public void executa() {
	// System.out.println("***************************************************");
	// System.out.println("-> Acessando p�gina: " + paginaInicial.getUrl());
	// System.out.println("***************************************************");
	// List<String> links = paginaInicial.getLinks();
	//
	// if (profundidade == -1) {
	// profundidade = links.size();
	// }
	//
	// if(linksGeral.size() > 0) {
	// imprimeLinks();
	// }
	//
	// for (int i = 0; i < profundidade; i++) {
	// if (!linksGeral.contains(links.get(i))) {
	// linksGeral.add(links.get(i));
	// paginaInicial = new PaginaHTML(links.get(i), palavraChave);
	// executa();
	// } else {
	// System.out.println("Link visitado.");
	// }
	// }
	// }
}
