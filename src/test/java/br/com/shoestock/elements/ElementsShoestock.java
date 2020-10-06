package br.com.shoestock.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import br.com.shoestock.utils.BaseWebPageFactory;

public class ElementsShoestock extends BaseWebPageFactory {
	public WebDriver driver;
	// Locators

	@FindBy(how = How.ID, using = "search-input")
	private WebElement searchTextbox;

	@FindBy(how = How.XPATH, using = "//button[@qa-automation='home-search-button']")
	private WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//*[@id='item-list']//span[contains(text(), 'Sandália')]/parent::a")
	private List<WebElement> listSandalias;

	@FindBy(how = How.ID, using = "buy-button-now")
	private WebElement buyButton;

	@FindBy(how = How.XPATH, using = "//a[@qa-auto='cart-buy-button']")
	private List<WebElement> continueShop;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Meu carrinho')]")
	private WebElement cartPage;
	
	
	
	public WebElement getSearchTextbox() {
		return searchTextbox;
	}
	
	public WebElement getSearchButton() {
		return searchButton;
	}
	public List<WebElement> getListSandalias() {
		return listSandalias;
	}
	
	public WebElement getCartPage() {
		return cartPage;
	}
	
	public WebElement getBuyButton() {
		return buyButton;
	}
	
	public WebElement getSizeButton(String size) {
		return this.driver.findElement(By.xpath("//*[@id='buy-box']//a[text()='" + size + "']"));
	}
	public ElementsShoestock(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}