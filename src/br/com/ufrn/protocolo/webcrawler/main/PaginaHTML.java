package br.com.ufrn.protocolo.webcrawler.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PaginaHTML {

	private String url;
	private String conteudo;
	private String palavraChave;
	private List<String> links;

	public PaginaHTML(String url) {
		super();
		this.url = url;
		this.conteudo = "";
		this.palavraChave = "";
		this.links = new ArrayList<String>();
		this.acessarPagina();
		this.percorreHTML();
	}

	public PaginaHTML(String url, String palavraChave) {
		super();
		this.url = url;
		this.palavraChave = palavraChave;
		this.conteudo = "";
		this.links = new ArrayList<String>();
		this.acessarPagina();
		this.percorreHTML();
	}

	public PaginaHTML() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public void acessarPagina() {
		try {
			URL u = new URL(url);
			InputStream in = u.openStream();
			int c;
			while ((c = in.read()) != -1) {
				// System.out.print((char) c);
				conteudo += (char) c;
			}
			in.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public void percorreHTML() {

		String trechoPadrao = "<a href=\"";
		int posicaoInicial = conteudo.indexOf(trechoPadrao) + trechoPadrao.length();

		int contador = 0;
		String link = "";
		while (contador < 1 && posicaoInicial < conteudo.length()) {
			if (conteudo.charAt(posicaoInicial + 1) == '"') {
				contador++;
			}
			link += conteudo.charAt(posicaoInicial);
			posicaoInicial++;
		}
		if (link.startsWith("http")) {
			if (palavraChave.equals("")) {
				links.add(link);
				System.out.println(link);
			} else {
				if (link.contains(palavraChave)) {
					links.add(link);
					System.out.println(link);
				}
			}
		}

		conteudo = conteudo.replaceFirst(trechoPadrao, "");

		if (conteudo.indexOf(trechoPadrao) > 0) {
			percorreHTML();
		}

	}

}
