package br.com.shoestock.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import framework.FrameworkDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	public enum Browsers {
		FIREFOX, CHROME, SAFARI
	}

	public enum MobilePlatformName {
		ANDROID, IOS
	}

	public static FrameworkDriver getDriver(Browsers browser) throws Exception {

		switch (browser) {
		case FIREFOX:
			File geckodriver = null;
			if (System.getProperty("os.name").contains("Window"))
				geckodriver = new File("src/test/resources/drivers/geckodriver.exe");
			else
				geckodriver = new File("src/test/resources/drivers/geckodriver");
			
			System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", false);

			return new FrameworkDriver(capabilities);
			//return new FirefoxDriver(capabilities);

		case CHROME:
			File chromedriver = null;
			if (System.getProperty("os.name").contains("Window"))
				chromedriver = new File("src/test/resources/drivers/chromedriver.exe");
			else
				chromedriver = new File("src/test/resources/drivers/chromedriver");
			
			System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			if (!System.getProperty("os.name").contains("Window"))
				options.addArguments("--start-fullscreen");
			//options.addArguments("--start-fullscreen");
			//TODO
			//return new ChromeDriver(options);
			return new FrameworkDriver(options);
		case SAFARI:

			//return new SafariDriver();

		default:
			return null;
		}

	}

	public static WebDriver getDriverMobile(MobilePlatformName mobilePlatformName, Device device) throws Exception {

		switch (mobilePlatformName) {
		case ANDROID:
			if (device.getEnvironment().toLowerCase().equals("device")) {

				killAppium();

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilePlatformName);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getVersion());
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);

				return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			} else {

				killAppium();

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilePlatformName);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getVersion());
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
				capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
				capabilities.setCapability("fastReset", true);
				capabilities.setCapability("avd", device.getDeviceName());

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-translate");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			}

		case IOS:

			if (device.getEnvironment().toLowerCase().equals("device")) {

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilePlatformName);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getVersion());
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
				capabilities.setCapability(MobileCapabilityType.UDID, device.getUdid());
				capabilities.setCapability("safariInitialUrl", "https://google.com");
				capabilities.setCapability("startIWDP", true);
				capabilities.setCapability("bootstrapPath","/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent");
				capabilities.setCapability("agentPath","/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");
				
				return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			} else {

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilePlatformName);
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceName());
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getVersion());
				capabilities.setCapability(MobileCapabilityType.UDID, device.getUdid());
				capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.SAFARI);
				//capabilities.setCapability("safariInitialUrl", "https://google.com");
				capabilities.setCapability("bootstrapPath",
						"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent");
				capabilities.setCapability("agentPath",
						"/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");

				return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			}

		default:
			return null;
		}

	}

	public static void killAppium() {
		String[] command = { "/usr/bin/killall", "-9", "node" };
		try {
			Runtime.getRuntime().exec(command);
			System.out.println("Appium server stopped.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
