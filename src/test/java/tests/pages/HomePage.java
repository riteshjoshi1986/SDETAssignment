package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class HomePage {
    private WebDriver driver;

    public void waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }

    public void jsScrollIntoView(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",element);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickHome() {
        driver.findElement(By.xpath("(//a[@href=\"index.html\"])[2]")).click();
    }

    public void clickContact() {
        driver.findElement(By.xpath("//a[text()=\"Contact\"]")).click();
    }

    public void clickAboutUs() {
        driver.findElement(By.xpath("//a[text()=\"About us\"]")).click();
    }

    public void clickCart() {
        driver.findElement(By.xpath("//a[text()=\"Cart\"]")).click();
    }

    public void clickLogin() {
        driver.findElement(By.id("login2")).click();
    }

    public void clickSignUp() {
        driver.findElement(By.id("signin2")).click();
    }

    public void clickLogOut() {

        WebElement logout = driver.findElement(By.id("logout2"));
        jsScrollIntoView(logout);
        logout.click();
        waitForElementVisible(driver,"//*[@id=\"login2\"]");
    }

    public void clickNext() {
        driver.findElement(By.id("next2")).click();
    }

    public void clickPrevious() {
        driver.findElement(By.id("prev2")).click();
    }

    public void selectCategory(String category) {
        WebElement element = driver.findElement(By.xpath("//a[text()=\""+category+"\"]"));
        jsScrollIntoView(element);
        element.click();
    }

    public void selectProduct(String product) {
        WebElement element = driver.findElement(By.xpath("//a[text()=\""+product+"\"]"));
        jsScrollIntoView(element);
        element.click();
    }

    public void enterLoginDetails(String username, String password) {
        waitForElementVisible(driver,"//*[@id=\"logInModalLabel\"]");
        driver.findElement(By.id("loginusername")).clear();
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()=\"Log in\"]")).click();
        waitForElementVisible(driver,"//*[@id=\"nameofuser\"]");
    }

    public String getLoginUser() {
        String loggedUser = driver.findElement(By.id("nameofuser")).getText();
        return loggedUser;
    }

}
