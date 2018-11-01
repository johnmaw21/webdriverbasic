package com.compendiumdev.test;

	import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;


import org.junit.AfterClass;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NotFoundException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedCondition;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class WebDriver81CookiesTest {

		private static WebDriver driver;
		private WebDriverWait wait;
		private WebElement searchBox;
		private WebElement searchButton;
		

		@BeforeClass
		public static void createDriver() {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		@Before
		public void refreshPage() {
			driver.get("http://www.compendiumdev.co.uk/selenium/search.php");	
			driver.manage().deleteAllCookies();
			refreshSearchPage();
		}
		
		public void refreshSearchPage() {
			searchBox = driver.findElement(By.name("q"));
			searchButton = driver.findElement(By.name("btnG"));
		}

		@Test
		public void webDriverGetCookieFromList() {

			searchBox.clear();
			searchBox.sendKeys("Cookie Test");
			searchButton.click();

			Set<Cookie> cookies = driver.manage().getCookies();
			for(Cookie aCookie : cookies) {
				if(aCookie.getName().contentEquals("seleniumSimplifiedSearchNumVisits")) {
					assertEquals("This should be my first visit", "1", aCookie.getValue());
				}
			}	
		}
		
		@Test
		public void webDriverGetCookieDirectly() {

			searchBox.clear();
			searchBox.sendKeys("Cookie Test");
			searchButton.click();

			Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
			assertEquals("This should be my first visit", "1", aCookie.getValue());
		}
		
		@Test
		public void webDriverUpdateClonedCookie() {
			
			searchBox.clear();
			searchBox.sendKeys("Cookie Test");
			searchButton.click();
			
			refreshSearchPage();
			
			Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
			assertEquals("This should be my first visit", "1", aCookie.getValue());
			
			// Clone Cookie and set Value to what is wanted
			Cookie aNewCookie = new Cookie( aCookie.getName(),
					String.valueOf(42),
					aCookie.getDomain(),
					aCookie.getPath(),
					aCookie.getExpiry(),aCookie.isSecure());
			
			driver.manage().deleteCookie(aCookie);
			driver.manage().addCookie(aNewCookie);
			
			searchBox.clear();
			searchBox.sendKeys("New Cookie Test");
			searchButton.click();
			
			aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
			assertEquals("This should be my first visit", "43", aCookie.getValue());
			
		}
		
		@Test
		public void webDriverUpdateCookieBuilder() {
			
			searchBox.clear();
			searchBox.sendKeys("Cookie Test");
			searchButton.click();
			
			refreshSearchPage();
			
			Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
			assertEquals("This should be my first visit", "1", aCookie.getValue());
			
			// Clone Cookie and set Value to what is wanted
			Cookie aNewCookie = new Cookie.Builder( aCookie.getName(), String.valueOf(29))
					.domain(aCookie.getDomain())
					.path(aCookie.getPath())
					.expiresOn(aCookie.getExpiry())
					.isSecure(aCookie.isSecure()).build();
			
			driver.manage().deleteCookie(aCookie);
			driver.manage().addCookie(aNewCookie);
			
			searchBox.clear();
			searchBox.sendKeys("Another Cookie Test");
			searchButton.click();
			
			aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
			assertEquals("This should be my first visit", "30", aCookie.getValue());
			
		}
		
		@AfterClass
		public static void tearDown() {
			driver.quit();
		}

}
