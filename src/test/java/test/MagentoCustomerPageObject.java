package test;

import com.endava.pages.magentoCustomer.MagentoCustomerHomePage;
import com.endava.pages.magentoCustomer.MagentoCustomerLoginPage;
import com.endava.pages.magentoCustomer.MagentoCustomerProductPage;
import com.endava.pages.magentoCustomer.MagentoCustomerProductsCategoryPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagentoCustomerPageObject {
    private WebDriver driver;
    private MagentoCustomerHomePage magentoCustomerHomePage;
    private String magentoCustomerEmail = "a2barcelona@yahoo.com";
    private String magentoCustomerPassword = "@Lex1234";
    private String magentoCustomerStoreView = "Alex Test Store View";
    private String magentoCustomerProductCategory = "Drinks";
    private String magentoCustomerProductName = "Brandy - Plum";
    private String magentoCustomerProductQuantity = "10";

    private static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

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
        magentoCustomerHomePage.changeTheStoreView(magentoCustomerStoreView);
        Assert.assertEquals("The store view was not changed", magentoCustomerStoreView, magentoCustomerHomePage.getCurrentStoreView());
        magentoCustomerHomePage.clearTheCart();
        Assert.assertTrue("The cart is not empty", magentoCustomerHomePage.cartIsEmpty());
        System.out.println("R3. The store view was successfully changed");

        System.out.println("S4. Go to Drinks Page and add a product to the cart");
        MagentoCustomerProductsCategoryPage magentoCustomerProductsCategoryPage = magentoCustomerHomePage.goToCategoryOfProducts("Drinks");
        Assert.assertTrue("The page for that category of products is not found", magentoCustomerProductsCategoryPage.isOpened());
        MagentoCustomerProductPage magentoCustomerProductPage = magentoCustomerProductsCategoryPage.goToProductPage("Brandy - Plum");
        Assert.assertTrue("The product does not exist", magentoCustomerProductPage.isOpened());
        magentoCustomerProductPage.addProductToCart("5");
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

    private void openPage() {
        System.out.println("S1. Open Magento Customer Home page");
        magentoCustomerHomePage = new MagentoCustomerHomePage(driver).get();
        Assert.assertTrue("Magento Customer Home page is not opened", magentoCustomerHomePage.isOpened());
        System.out.println("R1. Magento Customer Home page is opened");
    }
}