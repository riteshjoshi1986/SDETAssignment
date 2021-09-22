package tests.pagesTest;

import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tests.pages.HomePage;
import tests.pages.ProductPage;
import tests.pages.PlaceOrderPage;
import tests.setups.Setups;
import org.testng.Assert;

public class TestCases {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private PlaceOrderPage placeOrderPage;
    private Setups setups = new Setups();

    @BeforeTest
    public void setUp() {
        driver =  setups.setPropertyOS("Macbook","Firefox");
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        this.homePage = new HomePage(driver);
        this.productPage = new ProductPage(driver);
        this.placeOrderPage = new PlaceOrderPage(driver);
        setups.homePageSetup(driver);
    }

    @AfterMethod
    public void captureScreenFailed(ITestResult result) {

        if(ITestResult.FAILURE==result.getStatus()) {
            setups.captureScreenShot(driver,result.getName());
        }
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name="LoginUserPurchase")
    public Object[][] getDataLoggedUser(){
        return new Object[][]
                {
                        { "abc-test@abc.com","abcdef","Phones","Iphone 6 32gb","790", "John","USA", "St Louis", "412356789876", "10", "2022"}
                };

    }

    @DataProvider(name="GuestUserPurchase")
    public Object[][] getDataGuestUser(){
        return new Object[][]
                {
                        { "Monitors","Apple monitor 24","400", "Bishop","Mexico", "Mexico City", "31299456732", "11", "2021"},
                };

    }

    @DataProvider(name="LoginUserDelete")
    public Object[][] getDataLoggedUserDelete(){
        return new Object[][]
                {
                        { "xyz-test@xyz.com","xyz123","Phones","Iphone 6 32gb","790", "Phones","HTC One M9"}
                };

    }

    @Test (dataProvider = "LoginUserPurchase")
    public void TC1_LoggedUserPlaceOrder(String username, String pswd, String category, String product, String price, String name, String country, String city, String card, String month, String year){
        homePage.clickLogin();
        homePage.enterLoginDetails(username,pswd);
        Assert.assertEquals(homePage.getLoginUser(),"Welcome "+username);
        homePage.selectCategory(category);
        homePage.selectProduct(product);
        //Validate Product
        Assert.assertEquals(productPage.getProductName(),product);
        //Validate Price
        Assert.assertEquals(productPage.getProductPrice(),"$"+price+" *includes tax");
        productPage.clickAddtoCart();
        homePage.clickCart();
        placeOrderPage.clickPlaceOrder();
        placeOrderPage.enterOrderDetails(name, country,city,card,month,year);
        //Validate Purchase Details
        placeOrderPage.checkPurchaseMessage(name, price, card);
        homePage.clickLogOut();
    }

    @Test (dataProvider = "GuestUserPurchase")
    public void TC2_GuestUserPlaceOrder(String category, String product, String price, String name, String country, String city, String card, String month, String year){
        homePage.selectCategory(category);
        homePage.selectProduct(product);
        //Validate Product
        Assert.assertEquals(productPage.getProductName(),product);
        //Validate Price
        Assert.assertEquals(productPage.getProductPrice(),"$"+price+" *includes tax");
        productPage.clickAddtoCart();
        homePage.clickCart();
        placeOrderPage.clickPlaceOrder();
        placeOrderPage.enterOrderDetails(name, country,city,card,month,year);
        //Validate Purchase Details
        placeOrderPage.checkPurchaseMessage(name, price, card);
    }

    @Test (dataProvider = "LoginUserDelete")
    public void TC3_DeleteItem(String username, String pswd,String category, String product, String price, String dCategory, String dProduct){
        homePage.clickLogin();
        homePage.enterLoginDetails(username,pswd);
        Assert.assertEquals(homePage.getLoginUser(),"Welcome "+username);
        homePage.selectCategory(category);
        homePage.selectProduct(product);
        //Validate Product
        Assert.assertEquals(productPage.getProductName(),product);
        //Validate Price
        Assert.assertEquals(productPage.getProductPrice(),"$"+price+" *includes tax");
        productPage.clickAddtoCart();
        homePage.clickHome();
        homePage.selectCategory(dCategory);
        homePage.selectProduct(dProduct);
        productPage.clickAddtoCart();
        homePage.clickCart();
        placeOrderPage.clickDeleteOrder(dProduct);
        //Validate Delete Product
        placeOrderPage.checkOrderDeleted(dProduct);
    }

}
