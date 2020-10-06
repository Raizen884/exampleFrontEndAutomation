package br.com.userede.stepdefinition;

import com.github.javafaker.Faker;

import br.com.shoestock.functions.TestShoestock;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class DefaultSteps {

	//PageAdicionada _page;  Se quiser adicionar uma page a esses steps
	TestShoestock testFunctions;
	Faker _faker;

	public DefaultSteps() {
		//_page = new PageAdicionada(Hooks.driver);  Se quiser adicionar uma page a esses steps
		testFunctions = new TestShoestock(Hooks.driver);
		_faker = new Faker();
	}
	@Dado("^que eu acessei a pagina do shoestock$")
	public void que_eu_acessei_a_pagina_do_shoestock() throws Throwable {
		testFunctions.setUrl("https://www.shoestock.com.br/");
	}

	@Quando("^eu busco por \"([^\"]*)\"$")
	public void busco_por(String text) throws Throwable {
		testFunctions.setSearch(text);
	}
	
	
	@Quando("^clico no botao de busca$")
	public void clico_botao_busca() throws Throwable {
		testFunctions.clickSearchBtn();
	}

	
	@Entao("^aparecem resultados com sandálias$")
	public void aparece_resultados_com() throws Throwable {
		testFunctions.checkListSandalias();
	}
	
	@Quando("^incluo o item (\\d+), com tamanho (\\d+) no carrinho$")
	public void incluo_o_item_no_carrinho(int num, int size) throws Throwable {
	    testFunctions.includeItem(num);
	    testFunctions.addToCard(34);
	}
	
	@Entao("^o item é incluso ao carrinho$")
	public void o_item_é_incluso_ao_carrinho() throws Throwable {
	    testFunctions.verifyCart();
	}

}