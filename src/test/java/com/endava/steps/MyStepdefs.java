package com.endava.steps;//package com.endava.steps;
//
//import com.endava.ReusableWebDriver;
//import com.endava.pages.HomePage;
//import com.endava.pages.LaptopPage;
//import com.endava.pages.ProductPage;
//import com.endava.pages.SearchPage;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import org.junit.Assert;
//import org.openqa.selenium.WebDriver;
//
//
//public class MyStepdefs {
//    private WebDriver driver;
//    private HomePage homePage;
//    private ProductPage myLaptop;
//    private LaptopPage laptopPage;
//    private SearchPage searchPage;
//
//
//    public MyStepdefs(ReusableWebDriver driver)
//    {
//        this.driver = driver;
//        driver.manage().window().maximize();
//    }
//
//    @Given("that I open the emag page")
//    public void that_I_open_emag_page() {
//        homePage = new HomePage(driver);
//    }
//
//
//    @Then("^I am directed to the laptop page")
//    public void iAmDirectedToTheLaptopPage() throws Throwable {
//        Assert.assertEquals("Dashboard windows appeared", "laptop - cautare - eMAG.ro", driver.getTitle());
//    }
//
////    @After
////    public void tearDown() {
////        driver.quit();
////    }
//
//    @And("^I click on the first item")
//    public void iClickOnTheFirstItem() throws Throwable {
//        myLaptop = searchPage.clickAnItem();
//    }
//
////    @When("^I go to the Laptops page")
////    public void iGoToTheLaptopsPage() throws Throwable {
////        laptopPage = homePage.goToLaptops();
////    }
//
//
//    @And("^I click on the first laptop")
//    public void iClickOnTheFirstLaptop() throws Throwable {
//        myLaptop = laptopPage.clickaLaptop();
//    }
//
//    @Then("^The Item Page will display the \"([^\"]*)\" button")
//    public void theItemPageWillDisplayTheButton(String arg0) throws Throwable {
//        Assert.assertTrue("Adauga in cos” button is displayed", myLaptop.hasAdaugaToCos());
//    }
//
//    @When("^I search for \"([^\"]*)\"")
//    public void iSearchFor(String arg0) throws Throwable {
//        searchPage = homePage.searchFor("laptop");
//
//    }
//
//}
