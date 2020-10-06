package framework;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FrameworkDriver implements WebDriver {
	private WebDriver driver;
	private HashMap<String, String> propertyMap = new HashMap<String, String>();
	public FrameworkUtils utils;

	public FrameworkDriver() {
		this.driver = new ChromeDriver();
		this.utils = new FrameworkUtils();
		startMap("60");
	}

	public FrameworkDriver(ChromeOptions options) {
		this.driver = new ChromeDriver(options);
		this.utils = new FrameworkUtils();
		startMap("60");
	}

	public FrameworkDriver(DesiredCapabilities capabilities) {
		this.driver = new FirefoxDriver(capabilities);
		this.utils = new FrameworkUtils();
		startMap("60");
	}

	void startMap(String wait) {
		propertyMap.put("waitForElement", wait);
	}

	public WebElement findElement(By by) {
		return new FrameworkElement(propertyMap, this.utils, this.driver, by);

	}

	public Navigation navigate() {
		return new FrameworkNavigation(propertyMap, this.utils, this.driver.navigate());

	}

	public void quit() {
		this.driver.quit();
	}

	public void close() {
		this.driver.close();

	}

	public List<WebElement> findElements(By arg0) {
		return this.driver.findElements(arg0);
	}

	public void get(String arg0) {
		this.driver.get(arg0);

	}

	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}

	public String getPageSource() {
		return this.driver.getPageSource();
	}

	public String getTitle() {
		return this.driver.getTitle();
	}

	public String getWindowHandle() {
		return this.driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return this.driver.getWindowHandles();
	}

	public Options manage() {
		return this.driver.manage();
		// TODO implement manage class
	}

	public TargetLocator switchTo() {
		return this.driver.switchTo();
		// TODO implement TargetLocator Class;
	}

	public void setScenarioName(String scenario) {
		this.utils.setEvidencePath(scenario);
	}
}
