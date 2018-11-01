package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver1NavigationTest1 {

	private static WebDriver driver;
	
	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
		driver = new FirefoxDriver();
	}
	
	@Test
	public void navigationTests() {

		driver.get("http://www.compendiumdev.co.uk/selenium");
		assertTrue("Check Title is Selenium Simplified", driver.getTitle().contains("Selenium Simplified"));
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/search.php");
		assertThat("Selenium Simplified Search Engine", equalTo(driver.getTitle()));
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		assertEquals("Page Tile equals HTML Form Elements","HTML Form Elements", driver.getTitle());
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/basic_web_page.html");
		assertEquals("Page Tile equals HTML Form Elements","Basic Web Page Title", driver.getTitle());
		driver.navigate().to("http://www.compendiumdev.co.uk/selenium/refresh.php");
		String originalRefreshedTitle = driver.getTitle();
		assertTrue("Page Tile includes Refreshed", originalRefreshedTitle.contains("Refreshed Page"));
		driver.navigate().refresh();
		driver.navigate().back();
		assertEquals("Page Tile equals HTML Form Elements","Basic Web Page Title", driver.getTitle());
		driver.navigate().forward();
		String refreshedTitle = driver.getTitle();
		assertFalse("Page Not Same after Refreshed", refreshedTitle.equals(originalRefreshedTitle));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
