package com.compendiumdev.test;

	import static org.hamcrest.CoreMatchers.equalTo;
	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertFalse;
	import static org.junit.Assert.assertThat;
	import static org.junit.Assert.assertTrue;

	import java.io.File;
	import java.net.URISyntaxException;
	import java.util.List;

	import org.junit.AfterClass;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WebDriver32SelectTest {

		private static WebDriver driver;
		
		@BeforeClass
		public static void createDriver() {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
			driver = new FirefoxDriver();
			
			driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
		}
		
		@Test
		public void webDriverSelect() {
			
			// Multi-Select Items, Submit and check output
			
			WebElement selectValues;
			selectValues = driver.findElement(By.cssSelector("select[name='multipleselect[]']"));
			Select selectOptions = new Select(selectValues);
			
			assertTrue(selectOptions.isMultiple());
			assertThat(selectOptions.getAllSelectedOptions().size(), equalTo(1));
			selectOptions.deselectAll();
			assertThat(selectOptions.getAllSelectedOptions().size(), equalTo(0));
			selectOptions.selectByIndex(0);
			selectOptions.selectByValue("ms2");
			selectOptions.selectByVisibleText("Selection Item 3");
			assertThat(selectOptions.getAllSelectedOptions().size(), equalTo(3));
			
			WebElement submitButton;
			submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
			submitButton.click();
			new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
			assertThat(driver.findElement(By.id("_valuemultipleselect0")).getText(), equalTo("ms1"));
			assertThat(driver.findElement(By.id("_valuemultipleselect1")).getText(), equalTo("ms2"));
			assertThat(driver.findElement(By.id("_valuemultipleselect2")).getText(), equalTo("ms3"));	
		}
		
		@AfterClass
		public static void tearDown() {
			driver.quit();
		}
		
}
