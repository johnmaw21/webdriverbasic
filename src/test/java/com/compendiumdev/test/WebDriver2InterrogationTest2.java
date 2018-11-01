package com.compendiumdev.test;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriver2InterrogationTest2 {

	private static WebDriver driver;
	final private String PROTOCOL = "http://";
	final private String DOMAIN = "www.compendiumdev.co.uk/selenium";
	final private String ROOTURL = PROTOCOL + DOMAIN;
	
	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}
	
	@Test
	public void interrogationTestTwo() {

		driver.get(ROOTURL + "/find_by_playground.php");
		WebElement findById = driver.findElement(By.id("p11"));
		assertThat(findById.getText(), equalTo("This is k paragraph text"));
		WebElement findByLinkText = driver.findElement(By.linkText("jump to para 8"));
		assertThat(findByLinkText.getText(), equalTo("jump to para 8"));
		WebElement findByName = driver.findElement(By.name("liName17"));
		assertThat(findByName.getText(), equalTo("jump to para 16"));
		WebElement findByPartialLinkText = driver.findElement(By.partialLinkText("to para 20"));
		assertThat(findByPartialLinkText.getText(), equalTo("jump to para 20"));
		WebElement findByClassName = driver.findElement(By.className("nestedDiv"));
		assertThat(findByClassName.getAttribute("name"), equalTo("nestedDiv"));
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}


}

