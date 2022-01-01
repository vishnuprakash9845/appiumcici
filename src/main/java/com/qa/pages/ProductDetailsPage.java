package com.qa.pages;

import com.qa.BaseTest;
import com.qa.MenuPage;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductDetailsPage extends MenuPage {
	
	TestUtils utils = new TestUtils();

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[1]")
	private MobileElement SLBTitle;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child::XCUIElementTypeStaticText[2]")
	private MobileElement SLBText;

	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
	@iOSXCUITFindBy(id = "test-BACK TO PRODUCTS")
	private MobileElement backToProductsButton;

	@AndroidFindBy(accessibility = "test-Price")
	private MobileElement SLBPrice;
	
	@iOSXCUITFindBy (id = "test-ADD TO CART") 
	private MobileElement addToCartBtn;

	public String getSLBTitle() {
		return getText(SLBTitle,"Product Details Title is");
	}

	public String getSLBText() {
		return getText(SLBText,"Product Details Description is");
	}

	public ProductsPage pressBackToProductsButton() {
		click(backToProductsButton,"Navigates back to products page");
		return new ProductsPage();
	}

	public String getSLBPrice() {
		String price = getText(SLBPrice,"price is -");
		return price;
	}
	
	public String scrollToSLBPriceAndGetSLBPrice() {
		return getText(scrollToElement(),"After scroll price is -");
	}
	
	public void scrollPage() {
		iOSScrollToElement();
	}
	
	public Boolean isAddToCartBtnDisplayed() {
		return addToCartBtn.isDisplayed();
	}

}
