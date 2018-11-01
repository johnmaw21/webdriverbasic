package com.compendiumdev.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

	public static final String SELECTED_DRIVER = "selected.driver";
	
	public enum BrowserName{FIREFOX, CHROME, SAUCELABS, IE, EDGE, SAFARI}
	
	public static WebDriver get() {
		
		String getBrowserToUse="";
		BrowserName browserToUse;
		
		if(System.getProperties().containsKey(SELECTED_DRIVER)) {
			getBrowserToUse = System.getProperty(SELECTED_DRIVER);
		} else {getBrowserToUse = "FIREFOX";}
		
		browserToUse = BrowserName.valueOf(getBrowserToUse.toUpperCase());
		switch (browserToUse) {
		case FIREFOX :
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			return new FirefoxDriver();

		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			return new ChromeDriver();

		case IE:
			System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
			return new InternetExplorerDriver();
			
		default:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			return new FirefoxDriver();
		}
	}

}
