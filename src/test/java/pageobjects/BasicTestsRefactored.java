package pageobjects;

import com.compendiumdev.test.Driver;
import com.compendiumdev.test.BasicAjaxPageObject;
import com.compendiumdev.test.ProcessedFormPage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

import org.junit.After;

import static com.compendiumdev.test.BasicAjaxPageObject.Category;
import static com.compendiumdev.test.BasicAjaxPageObject.Language;

public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setupTest(){
        driver = Driver.get();
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(Language.JAVA);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", "23",processedFormPage.getValueFor("language_id"));

    }

    @Test
    public void chooseToCodeInJavascriptOnTheWeb(){

        basicAjaxPage.selectCategory(Category.WEB);
        basicAjaxPage.selectLanguage(Language.JAVASCRIPT);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Javascript code", "0",processedFormPage.getValueFor("language_id"));

    }

    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);
        basicAjaxPage.selectLanguage(Language.DESKTOP_CPP);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Cpp code", "10",processedFormPage.getValueFor("language_id"));
    }

    @After
    public void cleanUp() {
    	driver.quit();
    }
}
