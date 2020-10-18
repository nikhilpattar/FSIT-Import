package com.training.pom;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPOM 
{
	private WebDriver driver;
	
	public ProductsPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="/html/body/div/nav/ul/li[2]")
	public WebElement catalog;
	
	@FindBy(xpath="//*[@id=\"catalog\"]/ul/li[2]/a")
	public WebElement prodcutsLink;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement addProduct;
	
	@FindBy(id="input-name1")
	private WebElement productName;
	
	@FindBy(id="input-meta-title1")
	private WebElement metaTag;
	
	@FindBy(xpath="//*[@id=\"form-product\"]/ul/li[2]/a")
	private WebElement dataTab;
	
	@FindBy(id="input-model")
	private WebElement modelName;
	
	@FindBy(id="input-price")
	private WebElement price;
	
	@FindBy(id="input-quantity")
	private WebElement quantity;
	
	@FindBy(xpath="//*[@id=\"form-product\"]/ul/li[3]/a")
	private WebElement linksTab;
	
	@FindBy(id="input-manufacturer")
	private WebElement linkManufacturer;
	
	@FindBy(id="input-category")
	private WebElement linkCategory;
	
	@FindBy(xpath="//*[@class='btn btn-primary']")
	private WebElement saveProduct;
	
	//Discount Tab
	@FindBy(xpath="//*[@id=\"form-product\"]/ul/li[7]/a")
	private WebElement discountTab;
	
	@FindBy(xpath="//*[@data-original-title='Add Discount']")
	private WebElement addDiscount;
	
	@FindBy(xpath="//*[@name='product_discount[0][quantity]']")
	private WebElement discountQuantity;
	
	@FindBy(xpath="//*[@name='product_discount[0][price]']")
	private WebElement discountPrice;
	
	@FindBy(xpath="//*[@name='product_discount[0][date_start]']")
	private WebElement discountStartDate;
	
	@FindBy(xpath="//*[@id=\"discount-row0\"]/td[5]/div/span/button")
	private WebElement discountStartDateIcon;
	
	@FindBy(xpath="//*[@class='day active today']")
	private WebElement currentDate;
	
	@FindBy(xpath="//*[@name='product_discount[0][date_end]']")
	private WebElement discountEndDate;

	//Edit Products
	@FindBy(id="input-model")
	private WebElement productModel;
	
	@FindBy(id="button-filter")
	private WebElement productFilter;
	               
	@FindBy(xpath="//*[@id=\"form-product\"]/div/table/tbody/tr[1]/td[8]/a")
	private WebElement productEditIcon;
	
	public void sendProductModelDetails(String modelName) {
		this.productModel.sendKeys(modelName);
	}
	
	public void clickOnProductFilter() {
		this.productFilter.click();
	}
	
	public void clickOnProductEditIcon() {
		this.productEditIcon.click();
	}
	
	public void clickOnDiscountTab() {
		this.discountTab.click();
	}
	
	public void clickAddDiscount() {
		this.addDiscount.click();
	}
	
	public void sendDiscountQuantity(int quantity) {
		this.discountQuantity.sendKeys(Integer.toString(quantity));
	}
	
	public void sendDiscountPrice(int price) {
		this.discountPrice.sendKeys(Integer.toString(price));
	}
	
	public WebElement discountStartDate() {
		return this.discountStartDate;
	}
	
	public void clickOnDiscountStartIcon() {
		this.discountStartDateIcon.click();
	}
	
	public void selectCurrentDate() {
		this.currentDate.click();
	}
	
	public void sendDiscountStartDate(LocalDate date) {
		this.discountStartDate.sendKeys(date+"");
	}
	
	public void sendDiscountEndDate(LocalDate date, int plusDays) {
		this.discountEndDate.click();
		this.discountEndDate.sendKeys(date.plusDays(plusDays)+"");
	}
	
	public void clickAddproduct() {
		this.addProduct.click();
	}
	
	public void sendProductName(String productName) {
		this.productName.sendKeys(productName);
	}
	
	public void sendMetaTag(String metaTag) {
		this.metaTag.sendKeys(metaTag);
	}
	
	public void clickOnDataTab() {
		this.dataTab.click();
	}
	
	public void sendModelName(String modelName) {
		this.modelName.sendKeys(modelName);
	}
	
	public void sendPrice(int price) {
		this.price.clear();
		this.price.sendKeys(Integer.toString(price));
	}
	
	public void sendQuantity(int quantity) {
		this.quantity.clear();
		this.quantity.sendKeys(Integer.toString(quantity));
	}
	
	public void clickOnLinksTab() {
		this.linksTab.click();
	}
	
	public void linkManufacturer(String manufacturer) {
		this.linkManufacturer.sendKeys(manufacturer);
	}
	
	public void linkCategory(String category) {
		this.linkCategory.sendKeys(category);
	}
	
	public void saveProduct() {
		this.saveProduct.click();
	}
}
