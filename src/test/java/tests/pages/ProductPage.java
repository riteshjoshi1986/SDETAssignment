package tests.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ProductPage{
    private WebDriver driver;

    public void waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductName() {
        String ProductName = driver.findElement(By.xpath("//*[@class=\"name\"]")).getText();
        return ProductName;
    }

    public String getProductPrice() {
        String ProductPrice = driver.findElement(By.xpath("//*[@class=\"price-container\"]")).getText();
        return ProductPrice;
    }

    public void clickAddtoCart() {
        driver.findElement(By.xpath("//a[text()=\"Add to cart\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.alertIsPresent());
        //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Alert alert = driver.switchTo().alert();
        String mesg = driver.switchTo().alert().getText();
        if (mesg.contains("Product added")){
            alert.accept();
        }
    }

}
