package tests.setups;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Setups {
    private WebDriver driver;

    public WebDriver setPropertyOS(String OS, String browser){

        if (OS.equalsIgnoreCase("macbook")){
            driver = getPropertyForMacOS(browser);
        }else if(OS.equalsIgnoreCase("windows")){
            driver=getPropertyForWindows(browser);
        }else{
            System.out.println("OS Not defined");
        }
        return driver;
    }

    String path = System.getProperty("user.dir");

    public WebDriver getPropertyForMacOS(String browser) {
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", path+"/geckodriver");
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", path+"/chromeriver");
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("safari")){
            System.setProperty("webdriver.safari.driver", path+"/safaridriver");
            driver = new SafariDriver();
        }else{
            System.out.println("Browser Not defined");
        }
        return  driver;

    }

    public WebDriver getPropertyForWindows(String browser) {
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", path+"\\geckodriver.exe");
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", path+"\\chromedriver.exe");
            driver = new ChromeDriver();
        }else{
            System.out.println("Browser Not defined");
        }
        return  driver;
    }



    private void defaultSetup(WebDriver driver) {
        driver.get("https://www.demoblaze.com/index.html");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void homePageSetup(WebDriver driver) {
        defaultSetup(driver);
    }

    public void captureScreenShot(WebDriver driver, String fName){
        try {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(source, new File("Screenshots//"+fName+".png"));
            System.out.println("Screenshot taken");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }

    }

}
