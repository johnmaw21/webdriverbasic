package com.compendiumdev.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver72SynchCustomTest {

	
	
	private static WebDriver driver;
	private WebDriverWait wait;

	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//		driver = new ChromeDriver();
//		System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
//		driver = new InternetExplorerDriver();

	}
	
	@Test
	public void webDriverSynchInlineWait() {
		
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");
		wait = new WebDriverWait(driver, 10);
		
		// Select the Required Category
		WebElement category = driver.findElement(By.cssSelector("#combo1"));
		category.findElement(By.cssSelector("option[value='3']")).click();
		
		// Select the Required Language
//		wait.until(elementToBeClickable(By.cssSelector("option[value='23']")));
		WebElement option23 = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				return webDriver.findElement(By.cssSelector("option[value='23']"));
			}
		});
		option23.click();
		assertThat(option23.getText(), equalTo("Java"));
		
		// Press the Submit Button
		driver.findElement(By.cssSelector("input[name='submitbutton']")).click();
		
		// Check the Selected Language Id
		wait.until(visibilityOfElementLocated(By.cssSelector("#_valuelanguage_id")));
		assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), equalTo("23"));
	}
	
	@Test
	public void webDriverSynchCustomWait() {
		
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_redirect.html");
		wait = new WebDriverWait(driver, 15);
		
		//Select the 5 Second Link
		driver.findElement(By.cssSelector("#delaygotobasic")).click();
		wait.until(titleDoesNotContain("Basic Redirects"));
		assertThat(driver.getTitle(), equalTo("Basic Web Page Title"));
		driver.navigate().back();
		
		//Select the 2 Second Link
		wait.until(titleDoesNotContain("Basic Web Page Title"));
		driver.findElement(By.cssSelector("#delaygotobounce")).click();
		wait.until(titleDoesNotContain("Basic Redirects"));
		assertThat(driver.getTitle(), equalTo("Bounce"));
	}
		
	public static ExpectedCondition<Boolean> titleDoesNotContain(final String notInTitleString) {
		return new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				if(!webDriver.getTitle().contains(notInTitleString)) {
					return true;
				}
				return false;
			}
		};
	}
		
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}

