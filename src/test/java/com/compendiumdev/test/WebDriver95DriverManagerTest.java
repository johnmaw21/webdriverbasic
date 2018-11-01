package com.compendiumdev.test;

import static  org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver95DriverManagerTest {

	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void createAFirefoxDriver() {
		System.setProperty(Driver.SELECTED_DRIVER, Driver.BrowserName.FIREFOX.toString());
		assertBrowserTestRuns();
	}

	@Test
	public void createAChromeDriver() {
		System.setProperty(Driver.SELECTED_DRIVER, Driver.BrowserName.CHROME.toString());
		assertBrowserTestRuns();
	}

	@Test
	public void createADefaultDriver() {
		assertBrowserTestRuns();
	}

	public void assertBrowserTestRuns() {
		driver = Driver.get();
		wait = new WebDriverWait(driver, 15);
		driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
		wait.until(titleIs("Basic Web Page Title"));
	}

	@After
	public void quitDriver() {
		driver.quit();
	}

}
