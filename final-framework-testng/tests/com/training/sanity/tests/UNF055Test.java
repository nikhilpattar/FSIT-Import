package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.training.pom.ManufacturerPOM;
import com.training.pom.ProductsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UNF055Test
{
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private ProductsPOM productPOM;
	private ManufacturerPOM manufacturerPOM;
	private Actions act;
	private static Properties properties;
	private ScreenShot screenShot;

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
		manufacturerPOM = new ManufacturerPOM(driver);
		act = new Actions(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		
		// open the browser 
		driver.get(baseUrl);
	}
	
	@Test(dataProvider="xls-inputs",dataProviderClass=LoginDataProviders.class, priority=0)
	public void LoginAsAdmin(String userName, String passWord) {
		
		loginPOM.sendUserName(userName);
		loginPOM.sendPassword(passWord);
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("UNF_055_Loggedin");
	}	
	
	@Test(priority=1)
	public void addManufacturer() {
		
		
		act.moveToElement(manufacturerPOM.catalog()).moveToElement(manufacturerPOM.manufactureLink()).click().build().perform();
		
		manufacturerPOM.addManufacturer();
		manufacturerPOM.sendManufacturerName("Smart Uniforms");
		manufacturerPOM.saveManufacturer();
		screenShot.captureScreenShot("UNF_055_AddedManufacturer");
	}
	
	@Test(priority=2)
	public void addProduct() {
		
		act.moveToElement(productPOM.catalog).moveToElement(productPOM.prodcutsLink).click().build().perform();
		
		productPOM.clickAddproduct();
		productPOM.sendProductName("Socks");
		productPOM.sendMetaTag("Student Socks");
		
		productPOM.clickOnDataTab();
		productPOM.sendModelName("SCK-101");
		productPOM.sendPrice(200);
		productPOM.sendQuantity(3);
		productPOM.clickOnLinksTab();
		
		productPOM.linkManufacturer("Smart Uniforms");
		productPOM.linkCategory("Sports Uniform");
		productPOM.saveProduct();
		screenShot.captureScreenShot("UNF_055_AddedProduct");
	}
	

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
