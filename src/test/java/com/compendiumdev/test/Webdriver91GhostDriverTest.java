package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webdriver91GhostDriverTest {

	public static final File PHANTOMJS_EXE = new File("C:/Program Files/phantomjs-2.1.1-windows/phantomjs.exe");
	private static WebDriverWait wait;
	private static WebDriver driver;

	@BeforeClass
	public static void checkDependencies() {
		assertThat(PHANTOMJS_EXE.exists(), equalTo(true));

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("phantomjs.binary.path", PHANTOMJS_EXE.getAbsolutePath());
		driver = new PhantomJSDriver(caps);

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
