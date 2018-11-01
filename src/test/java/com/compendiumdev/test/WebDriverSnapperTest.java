package com.compendiumdev.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverSnapperTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void createAFirefoxDriver() {
		System.setProperty(Driver.SELECTED_DRIVER, Driver.BrowserName.FIREFOX.toString());
		driver = Driver.get();
		wait = new WebDriverWait(driver, 15);
	}

	@Test
	public void takeScreenShotAndSaveToFile() throws IOException {
//		if (((HasCapabilities) driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)) {

			driver.get("http://compendiumdev.co.uk/selenium");
			TakesScreenshot snapper = (TakesScreenshot) driver;

			File tempScreenshot = snapper.getScreenshotAs(OutputType.FILE);

			File myScreenshotDirectory = new File("C:\\temp\\screenshots\\");
			if (!myScreenshotDirectory.exists()) {
				myScreenshotDirectory.mkdirs();
			}

			File myScreenshot = new File(myScreenshotDirectory, "seleniumHomePage.png");
			FileUtils.moveFile(tempScreenshot, myScreenshot);

			assertTrue("The screenshot Length is greater than 0", myScreenshot.length() > 0);

			driver.get("file://" + myScreenshot.getAbsolutePath());
		}
//	}

	@After
	public void quitDriver() {
		driver.quit();
	}
}
