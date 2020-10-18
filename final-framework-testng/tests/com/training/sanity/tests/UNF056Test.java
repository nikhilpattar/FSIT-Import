package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.ProductsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UNF056Test 
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
		screenShot.captureScreenShot("UNF_056_Loggedin");
	}

	@Test(priority=1)
	public void editDataOfProduct() throws InterruptedException {

		act.moveToElement(productPOM.catalog).moveToElement(productPOM.prodcutsLink).click().build().perform();

		productPOM.sendProductModelDetails("SCK-101");
		productPOM.clickOnProductFilter();

		Thread.sleep(3000);

		productPOM.clickOnProductEditIcon();
		productPOM.clickOnDataTab();
		productPOM.sendQuantity(30);
	}

	@Test(priority=2)
	public void addDiscountToProduct() throws InterruptedException {

		productPOM.clickOnDiscountTab();
		productPOM.clickAddDiscount();
		productPOM.sendDiscountQuantity(1);
		productPOM.sendDiscountPrice(50);
		/*productPOM.discountStartDate().clear();
		productPOM.clickOnDiscountStartIcon();
		productPOM.selectCurrentDate();*/
		productPOM.sendDiscountStartDate(LocalDate.now());
		Thread.sleep(2000);
		productPOM.sendDiscountEndDate(LocalDate.now(), 1);
		screenShot.captureScreenShot("UNF_056_DiscountAdded");

		productPOM.saveProduct();
		screenShot.captureScreenShot("UNF_056_ProductEditedWithDiscount");
	}
	

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
