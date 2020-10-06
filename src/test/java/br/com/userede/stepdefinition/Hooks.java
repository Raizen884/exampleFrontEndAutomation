package br.com.userede.stepdefinition;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.openqa.selenium.WebDriver;

import br.com.shoestock.utils.Device;
import br.com.shoestock.utils.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.FrameworkDriver;
import framework.FrameworkWordEvidence;

public class Hooks {

	public static WebDriver driver;

	@Before("@firefox")
	public void openFirefoxBrowserDeskop() throws Exception {

		driver = DriverFactory.getDriver(DriverFactory.Browsers.FIREFOX);
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Before("@firefox-same-session")
	public void openFirefoxSameSessionBrowserDeskop(Scenario s) throws Exception {

		if (driver == null) {
			driver = DriverFactory.getDriver(DriverFactory.Browsers.FIREFOX);
			driver.manage().deleteAllCookies();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		((FrameworkDriver) driver).setScenarioName(s.getName());
	}

	@Before("@safari")
	public void openSafariBrowserDeskop() throws Exception {

		driver = DriverFactory.getDriver(DriverFactory.Browsers.SAFARI);
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Before("@chrome-same-session")
	public void openChromeBrowserDeskopSameSession(Scenario s) throws Exception {

		if (driver == null) {
			driver = DriverFactory.getDriver(DriverFactory.Browsers.CHROME);
			driver.manage().deleteAllCookies();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//driver.manage().window().maximize();
		}

		((FrameworkDriver) driver).setScenarioName(s.getName());
	}

	@Before("@chrome")
	public void openChromeBrowserDeskop() throws Exception {

		driver = DriverFactory.getDriver(DriverFactory.Browsers.CHROME);
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Before("@android-device")
	public void openBrowserMobileAndroidDevice() throws Exception {

		driver = DriverFactory.getDriverMobile(DriverFactory.MobilePlatformName.ANDROID,
				new Device("BY900FQCUE", "6.0", "", "", "device"));
		driver.manage().deleteAllCookies();

	}

	@Before("@android-emulator")
	public void openBrowserMobileAndroidEmulator() throws Exception {

		driver = DriverFactory.getDriverMobile(DriverFactory.MobilePlatformName.ANDROID,
				new Device("Nexus_5X_API_26", "8.0", "", "android", "emulator"));
		driver.manage().deleteAllCookies();

	}

	@Before("@ios-emulator")
	public void startDriverIOSEmulator() throws Exception {

		driver = DriverFactory.getDriverMobile(DriverFactory.MobilePlatformName.IOS,
				new Device("iPhone SE", "11.2", "F7A8519D-CF85-4A01-9953-C470BB0E6E37", "ios", "emulator"));
		driver.manage().deleteAllCookies();
	}

	@Before("@ios-device")
	public void startDriverIOSDevice() throws Exception {

		driver = DriverFactory.getDriverMobile(DriverFactory.MobilePlatformName.IOS,
				new Device("iPhone 5s", "11.2.6", "c536d1ca2a25894d7d42c45ddb266b37f9a0e455", "ios", "device"));
		driver.manage().deleteAllCookies();
	}

	@After("@closeBrowser")
	public void closeBrowser() throws Exception {

		try {

			driver.quit();

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	@After("@refreshBrowser")
	public void refreshBrowser() throws Exception {

		try {

			driver.navigate().refresh();
			;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@After("@generate-word")
	public void generateWord(Scenario s) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/properties/config.properties"));
		String path = ((FrameworkDriver) driver).utils.getEvidencePath();
		String fileName = ((FrameworkDriver) driver).utils.getExampleName();
		FrameworkWordEvidence evidence = new FrameworkWordEvidence();
		WordprocessingMLPackage template = evidence
				.getTemplate(prop.getProperty("templateWord"));
		evidence.createWordEvidence(template, path, fileName);
	}
	
}
