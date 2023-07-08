package com.asp.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.asp.constants.FrameworkConstants;
import com.asp.enums.ConfigProperties;
import com.asp.exceptions.BrowserInvocationFailedException;
import com.asp.listeners.WebEventListener;
import com.asp.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Driver class is responsible for invoking and closing the browsers.
 * 
 * It is also responsible for setting the driver variable to DriverManager which
 * handles the thread safety for the WebDriver instance.
 * 
 * 
 * @author Anjan S P
 * @see com.asp.driver.DriverManager
 */

public final class Driver {
	static String browser;
	static String grid = PropertyUtils.get(ConfigProperties.GRID);
	static String gridUrl = PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL);

	/**
	 * Private constructor to avoid external instantiation
	 */
	private Driver() {
	}

	static {
		browser = System.getProperty("browser");
		if (Objects.isNull(browser)) {
			browser = PropertyUtils.get(ConfigProperties.BROWSER);
		}
	}

	/**
	 * Gets the browser value and initialize the browser based on that. Browser
	 * value will be passed from ConfigFile
	 * 
	 * @author Anjan S P
	 * 
	 */
	public static void initDriver() {
		if (Objects.isNull(DriverManager.getDriver())) {
			WebDriver driver = null;
			if (grid.equalsIgnoreCase("yes")) {
				try {
					driver = getGridDriver();
				} catch (MalformedURLException e) {
					throw new BrowserInvocationFailedException("Failed to open browser in GRID. ", e);
				}
			} else {
				try {
					driver = getLocalDriver();
				} catch (SessionNotCreatedException e) {
					throw new BrowserInvocationFailedException("Failed to open the browser instance on Local. ", e);
				}
			}
			WebEventListener listener = new WebEventListener();
			WebDriver decorated = new EventFiringDecorator<WebDriver>(listener).decorate(driver);
			DriverManager.setDriver(decorated);
			DriverManager.getDriver().manage().window().maximize();
		} else if (isBrowserClosed(DriverManager.getDriver())) {
			/**
			 * This logic will help to launch the browser again if closed by manually.
			 * Useful when tests are running by @BeforeMethod launch browser and @AfterClass
			 * close the browser.
			 */
			DriverManager.unload();
			initDriver();
		}
	}

	/**
	 * Used to check if launched browser got closed
	 * 
	 * @param driver
	 * @return
	 */
	private static boolean isBrowserClosed(WebDriver driver) {
		try {
			driver.getTitle();
		} catch (WebDriverException e) {
			return true;
		}
		return false;
	}

	/**
	 * Terminates the browser instance. Sets the threadlocal to default value, i.e
	 * null.
	 * 
	 * @author Anjan S P
	 */
	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

	/**
	 * Used to open the browser on local
	 * 
	 * @return
	 */
	private static WebDriver getLocalDriver() {
		WebDriver driver;
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver(getChromeOptions(false));
			break;
		case "chrome-incognito":
			driver = new ChromeDriver(getChromeOptions(true));
			break;
		case "firefox":
			driver = new FirefoxDriver(getFirefoxOptions(false));
			break;
		case "firefox-private":
			driver = new FirefoxDriver(getFirefoxOptions(false));
			break;
		case "edge":
			driver = new EdgeDriver(getEdgeOptions(false));
			break;
		case "edge-inprivate":
			driver = new EdgeDriver(getEdgeOptions(true));
			break;
		default:
			driver = new EdgeDriver(getEdgeOptions(false));
			break;
		}
		return driver;
	}

	/**
	 * Used to open the browser on GRID
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	private static WebDriver getGridDriver() throws MalformedURLException {
		WebDriver driver;
		switch (browser) {
		case "chrome":
			driver = new RemoteWebDriver(new URL(gridUrl), getChromeOptions(false));
			break;
		case "chrome-incognito":
			driver = new RemoteWebDriver(new URL(gridUrl), getChromeOptions(true));
			break;
		case "firefox":
			driver = new RemoteWebDriver(new URL(gridUrl), getFirefoxOptions(false));
			break;
		case "firefox-private":
			driver = new RemoteWebDriver(new URL(gridUrl), getFirefoxOptions(false));
			break;
		case "edge":
			driver = new RemoteWebDriver(new URL(gridUrl), getEdgeOptions(false));
			break;
		case "edge-inprivate":
			driver = new RemoteWebDriver(new URL(gridUrl), getEdgeOptions(true));
			break;
		default:
			driver = new RemoteWebDriver(new URL(gridUrl), getEdgeOptions(false));
			break;
		}
		return driver;
	}

	/**
	 * Used to get the Chrome browser options
	 * 
	 * @param incognito
	 * @return
	 */
	private static ChromeOptions getChromeOptions(boolean incognito) {
		if (!grid.equalsIgnoreCase("yes")) {
			WebDriverManager.chromedriver().setup();
		}
		ChromeOptions co = new ChromeOptions();
		// Below code is to use particular location as data folder so that cookie and cache data can be stored.
//		co.addArguments("--user-data-dir=" + System.getProperty("user.dir") + "/BrowserProfiles/chrome/");
//		co.addArguments("--profile-directory=Automation");
		co.setAcceptInsecureCerts(true);
		if (incognito) {
			co.addArguments("--incognito");
		}
		Map<String, Object> prefs = new HashedMap<>();
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.default_directory", FrameworkConstants.getTempPath());
		co.setExperimentalOption("prefs", prefs);
		return co;
	}

	/**
	 * Used to get the Edge browser option
	 * 
	 * @param inprivate
	 * @return
	 */
	private static EdgeOptions getEdgeOptions(boolean inprivate) {
		if (!grid.equalsIgnoreCase("yes")) {
			WebDriverManager.edgedriver().setup();
		}
		EdgeOptions eo = new EdgeOptions();
		// Below code is to use particular location as data folder so that cookie and cache data can be stored.
//		eo.addArguments("--user-data-dir=" + System.getProperty("user.dir") + "/BrowserProfiles/edge/");
//		eo.addArguments("--profile-directory=Automation");
		eo.setAcceptInsecureCerts(true);
		if (inprivate) {
			eo.addArguments("-inprivate");
		}
		Map<String, Object> prefs = new HashedMap<>();
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.default_directory", FrameworkConstants.getTempPath());
		eo.setExperimentalOption("prefs", prefs);
		return eo;
	}

	/**
	 * Used to get the Firefox browser option
	 * 
	 * @param privateBrowser
	 * @return
	 */
	private static FirefoxOptions getFirefoxOptions(boolean privateBrowser) {
		if (!grid.equalsIgnoreCase("yes")) {
			WebDriverManager.firefoxdriver().setup();
		}
		FirefoxOptions options = new FirefoxOptions();
		// Below code is to use particular location as data folder so that cookie and cache data can be stored.
//		options.addArguments("--user-data-dir=" + System.getProperty("user.dir") + "/BrowserProfiles/firefox/");
//		options.addArguments("--profile-directory=Automation");
		options.setAcceptInsecureCerts(true);
		if (privateBrowser) {
			options.addArguments("-private");
		}
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", FrameworkConstants.getTempPath());
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.download.viewableInternally.enabledTypes", "");
		options.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/pdf;text/plain;application/text;text/xml;application/xml");
		options.addPreference("pdfjs.disabled", true);
		return options;
	}

}
