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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver61WindowsTest {

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
	public void webDriverWindowOne() {
				
		String firstWindowHandle = driver.getWindowHandle();
		assertThat(driver.getWindowHandles().size(), equalTo(1));
			
		driver.switchTo().frame("content");
		assertThat(driver.findElement(By.cssSelector("h1")).getText(), equalTo("Content")); 
			
		driver.findElement(By.cssSelector("#goevil")).click();
		assertThat(driver.getWindowHandles().size(), equalTo(2));
			
		Set<String> myHandles = driver.getWindowHandles();
		String secondWindowHandle = "";
			
		for(String aHandle : myHandles) {
			if(!firstWindowHandle.equals(aHandle)) {
				secondWindowHandle = aHandle; break;					
			}
		}
		driver.switchTo().window(secondWindowHandle);
		wait.until(ExpectedConditions.titleContains("Evil Tester"));
		
		driver.switchTo().window(firstWindowHandle);
		wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));

		//Tidy Up
		driver.switchTo().window(secondWindowHandle);
		driver.close();
		driver.switchTo().window(firstWindowHandle);
		
	}

	@Test
	public void webDriverWindowTwo() {
				
		String firstWindowHandle = driver.getWindowHandle();

		assertThat(driver.getWindowHandles().size(), equalTo(1));
			
		driver.switchTo().frame("content");
		driver.findElement(By.cssSelector("#goevil")).click();		
		driver.findElement(By.cssSelector("a[target='compdev']")).click();
		assertThat(driver.getWindowHandles().size(), equalTo(3));

		driver.switchTo().window("compdev");
		wait.until(ExpectedConditions.titleContains("Software Testing"));
		String compdevWindowHandle = driver.getWindowHandle();
		
		driver.switchTo().window("evil");
		wait.until(ExpectedConditions.titleContains("Evil Tester"));
		String evilWindowHandle = driver.getWindowHandle();
		
		driver.switchTo().window(firstWindowHandle);
		wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
		
		driver.switchTo().window(compdevWindowHandle);
		driver.close();
		assertThat(driver.getWindowHandles().size(), equalTo(2));
		
		driver.switchTo().window(evilWindowHandle);
		driver.close();
		assertThat(driver.getWindowHandles().size(), equalTo(1));
	}
	

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
