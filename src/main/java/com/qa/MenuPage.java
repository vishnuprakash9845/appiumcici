package com.qa;

import com.qa.pages.SettingsPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MenuPage extends BaseTest{

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView") 
	@iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
	private MobileElement settingsButton;
	
	public SettingsPage pressSettingsButton() throws Exception {
		utils.log().info("Press Settings button.");
		Thread.sleep(500);
		click(settingsButton);
		return new SettingsPage();
	}
}
