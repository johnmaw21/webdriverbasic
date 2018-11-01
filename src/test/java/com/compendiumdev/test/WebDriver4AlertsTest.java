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

public class WebDriver4AlertsTest {

	private static WebDriver driver;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/alerts.html");
	}

	@Test
	public void webDriverAlertsOne() {

		WebElement alertButton;
		alertButton = driver.findElement(By.cssSelector("input[value='Show alert box']"));
		alertButton.click();
		
		String messageText = "I am an alert box!";
		
		assertThat(messageText, equalTo(driver.switchTo().alert().getText()));
		driver.switchTo().alert().accept();
		
		alertButton.click();
		driver.switchTo().alert().dismiss();
	}
	
	@Test
	public void webDriverAlertsTwo() {

		WebElement alertButton;
		WebElement alertResponse;
		
		alertButton = driver.findElement(By.cssSelector("input[value='Show confirm box']"));
		alertButton.click();
		
		String messageText = "I am a confirm alert";
		
		assertThat(messageText, equalTo(driver.switchTo().alert().getText()));
		driver.switchTo().alert().accept();
		alertResponse = driver.findElement(By.cssSelector("#confirmreturn"));
		assertThat(alertResponse.getText(), equalTo("true"));
		
		alertButton.click();
		driver.switchTo().alert().dismiss();
		alertResponse = driver.findElement(By.cssSelector("#confirmreturn"));
		assertThat(alertResponse.getText(), equalTo("false"));
	}
	
	@Test
	public void webDriverAlertsThree() {

		WebElement alertButton;
		WebElement alertResponse;
		
		alertButton = driver.findElement(By.cssSelector("input[value='Show prompt box']"));
		alertButton.click();
		
		String messageText = "I prompt you";
		String newText = "My Prompt Text";
		
		assertThat(messageText, equalTo(driver.switchTo().alert().getText()));
		driver.switchTo().alert().sendKeys(newText);
		driver.switchTo().alert().accept();
		alertResponse = driver.findElement(By.cssSelector("#promptreturn"));
		assertThat(alertResponse.getText(), equalTo(newText));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
