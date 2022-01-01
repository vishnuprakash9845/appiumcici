package com.qa.pages;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BaseTest{
	
	TestUtils utils = new TestUtils();

	@AndroidFindBy(accessibility = "test-Username") 
	@iOSXCUITFindBy (id = "test-Username")
	private MobileElement usernameTextField;
	
	@AndroidFindBy(accessibility = "test-Password") 
	@iOSXCUITFindBy (id = "test-Password")
	private MobileElement passwordTextField;
	
	@AndroidFindBy(accessibility = "test-LOGIN") 
	@iOSXCUITFindBy (id = "test-LOGIN")
	private MobileElement loginButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") 
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
	private MobileElement errorText;

public LoginPage enterUserName(String username) {
	clear(usernameTextField,"Clearing UserName");
	sendKeys(usernameTextField,username,"UserName is : "+username);
	return this;
}

public LoginPage enterPassword(String password) {
	clear(passwordTextField,"Clearing Password");
	sendKeys(passwordTextField,password,"Password is : "+password);
	return this;
}

public ProductsPage pressLoginButton() {
	click(loginButton,"Press Login Button.");
	return new ProductsPage();
}

public ProductsPage login(String username, String password) {
	enterUserName(username);
	enterPassword(password);
	return pressLoginButton();
}

public String getErrorText(){
	return getText(errorText,"Error text is");
}

}
