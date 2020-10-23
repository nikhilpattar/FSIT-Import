package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManufacturerPOM 
{
	private WebDriver driver;
	
	public ManufacturerPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='alert alert-success']")
	private WebElement manufacturerAlertMessage;
	
	@FindBy(id="catalog")
	private WebElement catalog;
	
	@FindBy(xpath="//*[@id='catalog']/ul/li[7]/a")
	private WebElement manufacturerLink;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement addManufacturer;
	
	@FindBy(id="input-name")
	private WebElement manfacturerName;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement saveManufacturer;
	
	public WebElement catalog() {
		return this.catalog;
	}
	
	public String getManufacturerAlertMessage() {
		return this.manufacturerAlertMessage.getText();
	}
	
	public WebElement manufactureLink() {
		return this.manufacturerLink;
	}
	
	public void addManufacturer() {
		System.out.println(this.addManufacturer);
		this.addManufacturer.click();
	}
		
	public void sendManufacturerName(String manufacturerName) {
		this.manfacturerName.sendKeys(manufacturerName);
	}
	
	public void saveManufacturer() {
		this.saveManufacturer.click();
	}
}
