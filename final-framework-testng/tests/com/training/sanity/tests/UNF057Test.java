package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.ProductsPOM;
import com.training.utility.Constants;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UNF057Test 
{
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ProductsPOM productPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private Actions act;

	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver);
		productPOM = new ProductsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		act = new Actions(driver);
		// open the browser 
		driver.get(baseUrl);
	}

	@Test(dataProvider="xls-inputs",dataProviderClass=LoginDataProviders.class, priority=0)
	public void LoginAsAdmin(String userName, String passWord) throws InterruptedException {

		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(passWord);
		loginPOM.clickLoginBtn(); 
		
		Assert.assertEquals(driver.getTitle(), Constants.DASHBOARD_TITLE);
		
		screenShot.captureScreenShot("UNF_057_Loggedin");
	}

	@Test(priority=1,dependsOnMethods="LoginAsAdmin", alwaysRun=false)
	public void editDataAndLinksOfProduct() {

		act.moveToElement(productPOM.catalog).moveToElement(productPOM.prodcutsLink).click().build().perform();

		productPOM.clickAddproduct();
		productPOM.sendProductName("Shoes");
		productPOM.sendMetaTag("Shoes for students");

		productPOM.clickOnDataTab();
		productPOM.sendModelName("SHS-103");
		productPOM.sendPrice(1000);
		productPOM.sendQuantity(20);
		productPOM.clickOnLinksTab();

		productPOM.linkCategory("Sports Uniform");
	}

	@Test(priority=2, dependsOnMethods="editDataAndLinksOfProduct", alwaysRun=false)
	public void addDiscountToProduct() throws InterruptedException {
		productPOM.clickOnDiscountTab();
		productPOM.clickAddDiscount();
		productPOM.sendDiscountQuantity(2);
		productPOM.sendDiscountPrice(1000);
		productPOM.sendDiscountStartDate(LocalDate.now());
		productPOM.sendDiscountEndDate(LocalDate.now(), 1);
		screenShot.captureScreenShot("UNF_057_DiscountAdded");
		productPOM.saveProduct();
		
		Assert.assertEquals(productPOM.getProductAlertMessage(), Constants.PRODUCT_ALERT_MESSAGE);
		
		screenShot.captureScreenShot("UNF_055_AddedProduct");
	}


	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
