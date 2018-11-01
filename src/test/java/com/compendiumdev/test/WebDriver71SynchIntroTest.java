package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class WebDriver71SynchIntroTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Before
	public void setUp() {
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
		wait = new WebDriverWait(driver, 10);
		}
	
	@Test
	public void webDriverSynchIntro() {
		
		// Select the Required Category
		WebElement category = driver.findElement(By.cssSelector("#combo1"));
		category.findElement(By.cssSelector("option[value='3']")).click();
		
		// Select the Required Language
		wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
		WebElement language = driver.findElement(By.cssSelector("#combo2"));
		language.findElement(By.cssSelector("option[value='23']")).click();
		
		// Press the Submit Button
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		// Check the Selected Language Id
		wait.until(visibilityOfElementLocated(By.cssSelector("#_valuelanguage_id")));
		assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), equalTo("23"));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}

