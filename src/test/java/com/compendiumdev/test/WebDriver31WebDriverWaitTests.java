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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver31WebDriverWaitTests {

	private static WebDriver driver;
	
	@BeforeClass
	public static void createDriver() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");	
		driver = new FirefoxDriver();
	}
	
	@Before
	public void setUpPage() {
		driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");
	}
	
	@Test
	public void webDriverWaitTestOne() {

		// Submit the form and confirm Title changes to "Processed Form Details"
		driver.findElement(By.name("submitbutton")).submit();
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
	}
	
	@Test
	public void webDriverWaitTestTwo() {

		String addedComments = "New Added Comments";
		
		// Clear Form then Type comments, submit form and check output
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
		driver.findElement(By.name("comments")).clear();
		driver.findElement(By.name("comments")).sendKeys(addedComments);
		driver.findElement(By.name("submitbutton")).submit();
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
		assertThat(driver.findElement(By.id("_valuecomments")).getText(), equalTo(addedComments));
	}
	
	@Test
	public void webDriverWaitTestThree() {

		// Submit Form with Radio 2 selected and check output
		
		WebElement radioButton2;
		
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
		radioButton2 = driver.findElement(By.cssSelector("input[value='rd2']"));

		if (!radioButton2.isSelected()) {
			radioButton2.click();
		}
		WebElement submitButton;
		submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.click();
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
		assertThat(driver.findElement(By.id("_valueradioval")).getText(), equalTo("rd2"));
	}
	
	@Test
	public void webDriverWaitMultiSelectTest() {

		// Multi-Select Items, Submit and check output
		
		WebElement selectValues;
		
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
		selectValues = driver.findElement(By.cssSelector("select[name='multipleselect[]']"));
		List<WebElement> selectOptions = selectValues.findElements(By.tagName("option"));
		
		selectOptions.get(0).click();
		selectOptions.get(1).click();
		selectOptions.get(2).click();
		
		WebElement submitButton;
		submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.click();
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
		assertThat(driver.findElement(By.id("_valuemultipleselect0")).getText(), equalTo("ms1"));
		assertThat(driver.findElement(By.id("_valuemultipleselect1")).getText(), equalTo("ms2"));
		assertThat(driver.findElement(By.id("_valuemultipleselect2")).getText(), equalTo("ms3"));	
	}
	
	@Test
	public void  webDriverSendFilenameTest() throws URISyntaxException{

		// Input Filename and Submit, Check File has been sent
		// File will be in /src/test/resources so Browse Button will not be used
		
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("HTML Form Elements"));
		WebElement enterFileName;
		enterFileName = driver.findElement(By.cssSelector("input[type='file']"));
		
		File testFile = new File(this.getClass().getResource("/UKHO Notes.docx").toURI());
		enterFileName.sendKeys(testFile.getAbsolutePath());
		
		WebElement submitButton;
		submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitbutton']"));
		submitButton.click();
		
		new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));
		assertThat(driver.findElement(By.id("_valuefilename")).getText(), equalTo("UKHO Notes.docx"));
	}
		
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
