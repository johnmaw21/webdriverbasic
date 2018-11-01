package com.compendiumdev.test;

	import static org.hamcrest.CoreMatchers.equalTo;
	import static org.junit.Assert.assertThat;
	import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
	import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

	import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.phantomjs.PhantomJSDriver;
	import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WebDriver92SauceLabsTest {

		final static String Username = "johnmaw21";
		final static String AccessKey = "6fedea4a-5dd6-4f18-a22e-81398ddf4fee";
		final static String sauceLabsHub = "https://" + Username + ":" + AccessKey + "@ondemand.saucelabs.com:443/wd/hub";

		private static WebDriverWait wait;
		private static WebDriver driver;

		@BeforeClass
		public static void checkDependencies() {

			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "Windows 10");
			caps.setCapability("version", "59.0");
			try {
				driver = new RemoteWebDriver(new URL(sauceLabsHub), caps);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

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
