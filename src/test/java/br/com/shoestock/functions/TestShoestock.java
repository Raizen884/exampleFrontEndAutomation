package br.com.shoestock.functions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.shoestock.elements.ElementsShoestock;

public class TestShoestock {
	private ElementsShoestock page;
	public TestShoestock(WebDriver driver) {
		page = new ElementsShoestock(driver);
	}

	public void setUrl(String pUrl) {
		page.driver.navigate().to(pUrl);
	}
	
	public void setSearch(String searchText) {
		page.getSearchTextbox().sendKeys(searchText);
	}

	public void setFieldRegistration(String pRegistration) {
		//page.registrationField.sendKeys(pRegistration);
	}

	public void submitFieldContinue() {
		//page.submitFieldContinue.click();

	}

	public void clickSearchBtn() {
		page.getSearchButton().click();
		
	}

	public void checkListSandalias() {
		List<WebElement> elements = page.getListSandalias();
		Assert.assertTrue(elements.size() > 0);
	}

	public void includeItem(int num) {
		List<WebElement> elements = page.getListSandalias();
		elements.get(num).click();
		
	}

	public void addToCard(int size) {
		page.getSizeButton(String.valueOf(size)).click();
		page.getBuyButton().click();
	}

	public void verifyCart() {
		Assert.assertNotNull(page.getCartPage());
	}
	
}