package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class PlaceOrderPage {
    private WebDriver driver;

    public void waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }

    public PlaceOrderPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductName() {
        String ProductName = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")).getText();
        return ProductName;
    }

    public String getProductPrice() {
        String ProductPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[3]")).getText();
        return ProductPrice;
    }

    public void clickDeleteOrder(String tobeDeleted) {
        waitForElementVisible(driver,"//td[text()=\""+tobeDeleted+"\"]/following-sibling::td/following-sibling::td/a[text()=\"Delete\"]");
        driver.findElement(By.xpath("//td[text()=\""+tobeDeleted+"\"]/following-sibling::td/following-sibling::td/a[text()=\"Delete\"]")).click();
    }

    public boolean isOrderDeleted(String tobeDeleted) {
        try {
            driver.findElement(By.xpath("//td[text()=\""+tobeDeleted+"\"]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void checkOrderDeleted(String tobeDeleted) {
        Assert.assertTrue(isOrderDeleted(tobeDeleted));
    }

    public void clickPlaceOrder() {
        waitForElementVisible(driver,"//button[text()=\"Place Order\"]");
        driver.findElement(By.xpath("//button[text()=\"Place Order\"]")).click();
    }

    public void enterOrderDetails(String name, String country, String city, String card, String month, String year) {
        waitForElementVisible(driver,"//*[@id=\"orderModalLabel\"]");
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).clear();
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).clear();
        driver.findElement(By.id("card")).sendKeys(card);
        driver.findElement(By.id("month")).clear();
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).clear();
        driver.findElement(By.id("year")).sendKeys(year);
        driver.findElement(By.xpath("//button[text()=\"Purchase\"]")).click();
    }

    public void checkPurchaseMessage(String name, String price, String card) {
        waitForElementVisible(driver,"//*[@class=\"sweet-alert  showSweetAlert visible\"]");
        Assert.assertEquals("Thank you for your purchase!", driver.findElement(By.xpath("//*[@class=\"sweet-alert  showSweetAlert visible\"]//h2")).getText());
        String details = driver.findElement(By.xpath("//*[@class=\"sweet-alert  showSweetAlert visible\"]//p")).getText();
        System.out.println(details);
        driver.findElement(By.xpath("//button[text()=\"OK\"]")).click();
        Assert.assertTrue(details.contains(name));
        Assert.assertTrue(details.contains(price));
        Assert.assertTrue(details.contains(card));

    }
    
}
