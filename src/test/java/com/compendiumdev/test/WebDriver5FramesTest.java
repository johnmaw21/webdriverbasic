package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class WebDriver5FramesTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Before
	public void setUpWebPage() {
		driver.get("http://www.compendiumdev.co.uk/selenium/frames");
		wait = new WebDriverWait(driver, 10);
		
	}

	@Test
	public void webDriverFramesOne() {
		
		driver.switchTo().frame("content");
		assertThat(driver.findElement(By.cssSelector("h1")).getText(), equalTo("Content")); 
		
		driver.findElement(By.cssSelector("a[href='green.html']")).click();
		wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));
		
		driver.findElement(By.cssSelector("a[href='content.html']")).click();
		wait.until(presenceOfElementLocated(By.xpath("//h1[.='Content']")));
		assertThat(driver.findElement(By.tagName("h1")).getText(), equalTo("Content"));
	}

	@Test
	public void webDriverFramesTwo() {
		
		driver.switchTo().frame("menu");
		driver.findElement(By.cssSelector("a[href='iframe.html']")).click();
		new WebDriverWait(driver, 10).until(titleIs("HTML Frames Example - iFrame Contents"));
		
		// Current Firefox Browser does not support inline frames
//		driver.switchTo().frame(0);
//		String pageSource = driver.getPageSource();
//		driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();
//		wait.until(titleIs("FrameSet Example Title (Example 5)"));
		
//		driver.switchTo().frame("content");
//		driver.findElement(By.cssSelector("a[href='index.html']"));
//		wait.until(titleIs("FrameSet Example Title (Example 6)"));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
