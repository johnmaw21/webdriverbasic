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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver62ManageWindowsTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
	}

	@Test
	public void webDriverManageOne() {

		Window win = driver.manage().window();
		win.maximize();	
	}

	@Test
	public void webDriverManageTwo() throws InterruptedException {

		Window win = driver.manage().window();
		win.setSize(new Dimension(1000,500));
		win.setPosition(new Point(500,250));
		for (int i=1; i < 20; i++) {
			win.setPosition(new Point(500,100));
			Thread.sleep(500);
			win.setPosition(new Point(500,300));
		}
		win.setPosition(new Point(500,250));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
