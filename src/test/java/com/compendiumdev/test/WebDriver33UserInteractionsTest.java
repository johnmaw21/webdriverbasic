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

public class WebDriver33UserInteractionsTest {

	private static WebDriver driver;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("http://www.compendiumdev.co.uk/selenium/gui_user_interactions.html");
	}

	@Before
	public void resetPage() {
		driver.navigate().refresh();
		// Click on HTML to bring the page into Focus before each test if
		// synchronisation issues occur
		driver.findElement(By.tagName("html")).click();
	}

	@Test
	public void webDriverUserInteractionOne() {

		WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));
		WebElement droppable1 = driver.findElement(By.cssSelector("#droppable1"));

		new Actions(driver).clickAndHold(draggable1).moveToElement(droppable1).release().perform();
		assertThat(droppable1.getText(), equalTo("Dropped!"));
	}

	@Test
	public void webDriverUserInteractionTwo() {

		WebElement draggable2 = driver.findElement(By.cssSelector("#draggable2"));
		WebElement droppable1 = driver.findElement(By.cssSelector("#droppable1"));

		new Actions(driver).dragAndDrop(draggable2, droppable1).release().perform();
		assertThat(droppable1.getText(), equalTo("Get Off Me!"));
	}

	@Test
	public void webDriverUserInteractionThree() {

		WebElement draggable1 = driver.findElement(By.cssSelector("#draggable1"));

		new Actions(driver).keyDown(Keys.CONTROL).sendKeys("b").keyUp(Keys.CONTROL).perform();
		assertThat(draggable1.getText(), equalTo("Bwa! Ha! Ha!"));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}
