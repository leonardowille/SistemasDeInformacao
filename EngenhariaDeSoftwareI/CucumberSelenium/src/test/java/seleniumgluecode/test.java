package seleniumgluecode;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class test {
    private static WebDriver driver;

    @Given("^user is on homepage$")
    public void userIsOnHomepage() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\tools\\chromedriver_74.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com.br");
    }

    @When("^user search about \"([^\"]*)\"$")
    public void userSearchAbout(String contentSearch) throws Throwable {
        driver.findElement(By.name("q")).sendKeys(contentSearch);
        driver.findElement(By.name("btnK")).click();
    }

    @Then("^has many links displayed$")
    public void hasManyLinksDisplayed() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".r"));
        Assert.assertTrue(elements.size() > 0);
        driver.quit();
    }

    @When("^user search about \"([^\"]*)\" with ESTOU COM SORTE$")
    public void userSearchAboutWithESTOUCOMSORTE(String contentSearch) throws Throwable {
        driver.findElement(By.name("q")).sendKeys(contentSearch);
        driver.findElement(By.name("btnI")).click();
    }

    @Then("^opened another site$")
    public void openedAnotherSite() {
        Assert.assertFalse(driver.getCurrentUrl().contains("google.com"));
        driver.quit();
    }
}