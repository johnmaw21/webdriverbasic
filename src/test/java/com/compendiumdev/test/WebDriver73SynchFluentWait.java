package com.compendiumdev.test;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.util.function.Function;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver73SynchFluentWait {

	private static WebDriver driver;
	private WebDriverWait wait;
	private FluentWait fWait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.compendiumdev.co.uk/selenium/javascript_countdown.html");
	}

	@Before
	public void refreshPage() {
		driver.navigate().refresh();
	}
	

	@Test
	public void webDriverSynchFluentElement() {

		WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));

		String theTime = new FluentWait<WebElement>(countdown).
				withTimeout(Duration.ofSeconds(10)).
				pollingEvery(Duration.ofMillis(100)).
				until(new Function<WebElement, String>() {

					@Override
					public String apply(WebElement element) {
						return element.getText().endsWith("04") ? element.getText() : null;
					}
				});
		assertThat(theTime, equalTo("01:01:04"));	
	}
	
	@Test
	public void webDriverSynchFluentDriver() {

		String theTime = new WebDriverWait(driver, 10, 100).
				until(new ExpectedCondition<String>()  {
					@Override
					public String apply(WebDriver driver)  {
						WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));
						return countdown.getText().endsWith("04") ? countdown.getText() : null;
					}
				}
				);			
		assertThat(theTime, equalTo("01:01:04"));					
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
