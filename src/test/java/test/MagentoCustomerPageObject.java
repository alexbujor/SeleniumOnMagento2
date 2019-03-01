package test;

import com.endava.pages.magentoCustomer.FirstProductFromDrinksPage;
import com.endava.pages.magentoCustomer.MagentoCustomerDrinksPage;
import com.endava.pages.magentoCustomer.MagentoCustomerHomePage;
import com.endava.pages.magentoCustomer.MagentoCustomerLoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagentoCustomerPageObject {
    WebDriver driver;
    MagentoCustomerHomePage magentoCustomerHomePage;
    private String magentoCustomerEmail = "a2barcelona@yahoo.com";
    private String magentoCustomerPassword = "@Lex1234";
    public static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

    @Test
    public void makeAnOrder() throws InterruptedException {
        System.out.println("S2. Go to the Login page and sign in with your credentials");
        MagentoCustomerLoginPage magentoCustomerLoginPage = magentoCustomerHomePage.pressSignInButton();
        Assert.assertTrue("Magento Customer Login Page is not opened", magentoCustomerLoginPage.isOpened());
        magentoCustomerLoginPage.fillLoginFields(magentoCustomerEmail, magentoCustomerPassword);
        magentoCustomerHomePage = magentoCustomerLoginPage.pressSignInButton();
        Assert.assertTrue("Magento Customer Home page is not opened", magentoCustomerHomePage.isOpened());
        System.out.println("R2. Successfully Signed in using my credentials");

        System.out.println("S3. Change the store view and clear the cart in case it's not empty");
        magentoCustomerHomePage.changeToAlexStoreView();
        Assert.assertEquals("The store view was not changed", "Alex Test Store View", magentoCustomerHomePage.getCurrentStoreView());
        if (!magentoCustomerHomePage.cartIsEmpty()) {
            magentoCustomerHomePage.clearTheCart();
        }
        Assert.assertTrue("The cart is not empty", magentoCustomerHomePage.cartIsEmpty());
        System.out.println("R3. The store view was successfully changed");

        System.out.println("S4. Go to Drinks Page and add a product to the cart");
        MagentoCustomerDrinksPage magentoCustomerDrinksPage = magentoCustomerHomePage.goToDrinksProducts();
        Assert.assertTrue("Magento Drinks Page is not opened", magentoCustomerDrinksPage.isOpened());
        FirstProductFromDrinksPage firstProductFromDrinksPage = magentoCustomerDrinksPage.clickOnTheFirstProduct();
        Assert.assertTrue("The page of the first product is not opened", firstProductFromDrinksPage.isOpened());
        firstProductFromDrinksPage.addProductToCart("5");
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_CHROMEDRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        openPage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void openPage() {
        System.out.println("S1. Open Magento Customer Home page");
        magentoCustomerHomePage = new MagentoCustomerHomePage(driver).get();
        Assert.assertTrue("Magento Customer Home page is not opened", magentoCustomerHomePage.isOpened());
        System.out.println("R1. Magento Customer Home page is opened");
    }
}