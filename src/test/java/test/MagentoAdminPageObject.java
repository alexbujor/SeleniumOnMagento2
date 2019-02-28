package test;

import com.endava.pages.MagentoAdminConfigurationPage;
import com.endava.pages.MagentoAdminDashboardPage;
import com.endava.pages.MagentoAdminLoginPage;
import com.endava.pages.MagentoAdminPaymentMethodsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MagentoAdminPageObject {
    private WebDriver driver;
    private MagentoAdminLoginPage magentoAdminLoginPage;
    private String magentoAdminUsername = "abujor";
    private String magentoAdminPassword = "@lex123";
    private String title = "Elavon";
    private String enabled = "Yes";
    private String paymentAction = "Authorize";
    private String integrationMethod = "Hosted Payment Page (PCI SAQ A)";
    private static final String PATH_TO_CHROMEDRIVER = "C:/chromedriver.exe";

    @Test
    public void changeConvergePaymentSettingsForAlexStoreView() throws InterruptedException {
        System.out.println("S2. Log In with your credentials");
        magentoAdminLoginPage.fillLoginFields(magentoAdminUsername, magentoAdminPassword);
        MagentoAdminDashboardPage magentoAdminDashboardPage = magentoAdminLoginPage.pressSignInButton();
        Assert.assertTrue("Magento Admin Dashboard page is not opened", magentoAdminDashboardPage.isOpened());
        System.out.println("R2. Magento Admin Dashboard page is opened");

        System.out.println("S3. Go to Magento Admin Configuration");
        magentoAdminDashboardPage.closePopUp();
        MagentoAdminConfigurationPage magentoAdminConfigurationPage = magentoAdminDashboardPage.goToConfigPage();
        Assert.assertTrue("Magento Admin Configuration page is not opened", magentoAdminConfigurationPage.isOpened());
        System.out.println("R3. Magento Admin Configuration page is opened");

        System.out.println("S4. Go to Magento Admin Payment Methods Tab");
        magentoAdminConfigurationPage.collapseGeneralTab();
        MagentoAdminPaymentMethodsPage magentoAdminPaymentMethodsPage = magentoAdminConfigurationPage.goToPaymentMethods();
        Assert.assertTrue("Magento Admin Payment Methods page is not opened", magentoAdminPaymentMethodsPage.isOpened());
        System.out.println("R4. Magento Admin Payment Methods page is opened");

        System.out.println("S5. Configure a store view and save the changes");
        magentoAdminPaymentMethodsPage.changeToAlexStoreView();
        Assert.assertEquals("The store view was not changed", "Alex Test Store View", magentoAdminPaymentMethodsPage.getCurrentStoreView());
        magentoAdminPaymentMethodsPage.changeConvergePaymentSettings(title, enabled, paymentAction, integrationMethod, "", "");
        magentoAdminPaymentMethodsPage.saveTheConfiguration();
        Assert.assertTrue("Configuration not saved successfully", magentoAdminPaymentMethodsPage.isSavedSuccessfully());
        System.out.println("R5. Configuration successfully changed for that store view");
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
        System.out.println("S1. Open Magento Admin Login page");
        magentoAdminLoginPage = new MagentoAdminLoginPage(driver).get();
        Assert.assertTrue("Magento Admin Login page is not opened", magentoAdminLoginPage.isOpened());
        System.out.println("R1. Magento Admin Login page is opened");
    }
}