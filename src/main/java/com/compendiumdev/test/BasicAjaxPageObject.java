package com.compendiumdev.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject {
    private WebDriver driver;

    
    	@FindBy(how = How.ID, using="combo1")
    	private WebElement categorySelectBy;
    	
    	@FindBy(how = How.ID, using="combo2")
    	private WebElement languageSelectBy;
    	
    	@FindBy(how = How.NAME, using="submitbutton")
    	private WebElement codeInItBy;

    public enum Category {
        WEB(1), DESKTOP(2), SERVER(3);

        private int dropDownValue;

        Category(int value) {
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
    }

    public enum Language {
        JAVASCRIPT(0), VBSCRIPT(1), FLASH(2),
        DESKTOP_CPP(10), ASSEMBLER(11), C(12), VISUALBASIC(13),
        COBOL(20), FORTRAN(2), SERVER_CPP(22), JAVA(23);

        private int dropDownValue;

        Language(int value) {
            this.dropDownValue = value;
        }

        public int value(){
            return dropDownValue;
        }
    }
    public BasicAjaxPageObject(WebDriver aDriver) {
        driver = aDriver;
        PageFactory.initElements(driver, this);
    }

    public void get() {
        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    public void selectCategory(Category category) {
//        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelectBy.findElement(By.cssSelector("option[value='" + category.value() + "']")).click();

        // wait until the option I want to click is present
        // we could also wait for the contents of the drop down to fill
        /*new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("option[value='23']")));*/

        // instead wait for the ajax symbol

        // wait until the ajax symbol has gone
        // because then the drop down has populated
        new WebDriverWait(driver,10).until(ajaxActionIsComplete());
    }

    public void selectLanguage(Language language) {
//        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelectBy.findElement(By.cssSelector("option[value='" + language.value() + "']")).click();
    }

    public void clickCodeInIt() {
        // Submit the form
//        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInItBy.click();
    }

    public ExpectedCondition<Boolean> ajaxActionIsComplete() {
        return ExpectedConditions.invisibilityOfElementLocated(
                By.id("ajaxBusy"));
    }

}
