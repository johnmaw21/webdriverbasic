package com.compendiumdev.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver2InterrogationTest1 {

	private static WebDriver driver;
	
	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
		driver = new FirefoxDriver();
	}
	
	@Test
	public void interrogationTestOne() {

		final String theTestPageURL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";
		driver.navigate().to(theTestPageURL);
		assertThat(driver.getTitle(), equalTo("Basic Web Page Title"));
		assertThat(driver.getCurrentUrl(), equalTo(theTestPageURL));
		assertThat(driver.getPageSource(), containsString("A paragraph of text"));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}


}
