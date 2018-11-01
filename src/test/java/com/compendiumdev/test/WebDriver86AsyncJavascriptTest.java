package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver86AsyncJavascriptTest {

	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
	}

	@Before
	public void refreshPage() {
		driver.navigate().refresh();
	}

	@Test
	public void buildTimeDelay() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		long startTime = System.currentTimeMillis();
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length-1], 2500);");
		long endTime = System.currentTimeMillis();
		
		assertTrue("Time has lapsed by 2500 seconds", endTime >= startTime + 2500 );
		long difference = endTime - startTime;
		System.out.println("Difference is: " + difference);
	}
	
	@Test
	public void sendHTTPRequestAndWaitForResponse() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		Object response1 = js.executeAsyncScript(
			"var callback = arguments[arguments.length - 1];" +
					"var xhr = new XMLHttpRequest();" +
					"xhr.open('GET', '/selenium/ajaxselect.php?id=1', true);" +
					"xhr.onreadystatechange = function() {" +
					"  if (xhr.readyState == 4) { " +
					"    callback(xhr.responseText);" +
					"  }" +
					"};" +
					"xhr.send();");
	
		Object response2 = js.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];" +
						"var xhr = new XMLHttpRequest();" +
						"xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
						"xhr.onreadystatechange = function() {" +
						"  if (xhr.readyState == 4) { " +
						"    callback(xhr.responseText);" +
						"  }" +
						"};" +
						"xhr.send();");
		
		Object response3 = js.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];" +
						"var xhr = new XMLHttpRequest();" +
						"xhr.open('GET', '/selenium/ajaxselect.php?id=3', true);" +
						"xhr.onreadystatechange = function() {" +
						"  if (xhr.readyState == 4) { " +
						"    callback(xhr.responseText);" +
						"  }" +
						"};" +
						"xhr.send();");
		
		System.out.println((String) response1);
		System.out.println((String) response2);
		System.out.println((String) response3);
		
	}
	

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
}