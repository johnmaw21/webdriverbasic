package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver85JavascriptTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/canvas_basic.html");

	}

	@Before
	public void refreshPage() {
		driver.navigate().refresh();

	}

	@Test
	public void drawShapeAndVerify() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertThat(actionsCount, equalTo(2));

		for (int testLoop = 0; testLoop < 10; testLoop++) {

			js.executeScript("draw(0, arguments[0], arguments[1], 20, arguments[2]);", testLoop * 20, testLoop * 20,
					"#" + testLoop + testLoop + "0000");
		}

		actionsCount = driver.findElements(By.cssSelector("#commandlist li")).size();
		assertThat(actionsCount, equalTo(12));
	}

	@Test
	public void drawAddTwoNumberseAndVerify() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		long addResult = (long) js.executeScript("return (arguments[0]+arguments[1]);", 15, 10);
		assertThat(addResult, equalTo(25L));
	}

	@Test
	public void changeTitleUsingJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		assertEquals("Javascript Canvas Example", driver.getTitle());

		js.executeScript("document.title=arguments[0]", "bob");

		assertEquals("bob", driver.getTitle());
	}

	@Test
	public void useJQueryToHideBodyWithNoParams() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

		js.executeScript("$('body').hide();");

		assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
	}

	@Test
	public void useJQueryToHideBodyUsingParams() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

		js.executeScript("$(arguments[0]).hide();", driver.findElement(By.cssSelector("#commands")));

		assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());
	}

	@Test
	public void javascriptAddsSomethingToPageAndLeavesItThere() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// this code runs as an anonymous function and leaves no trace
		js.executeScript("alert('alert triggered by webdriver')");

		assertThat(driver.switchTo().alert().getText(), equalTo("alert triggered by webdriver"));
		driver.switchTo().alert().accept();

		// this code creates a function that will persist as we have added it to the
		// global window
		js.executeScript("window.webdriveralert = function() {alert('stored alert triggered by webdriver')};"
				+ "window.webdriveralert.call();");

		assertThat(driver.switchTo().alert().getText(), equalTo("stored alert triggered by webdriver"));
		driver.switchTo().alert().accept();

		// this can only succeed if we have managed to leave the javascript lying around
		js.executeScript("window.webdriveralert.call();");
		assertThat(driver.switchTo().alert().getText(), equalTo("stored alert triggered by webdriver"));
		driver.switchTo().alert().accept();
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
