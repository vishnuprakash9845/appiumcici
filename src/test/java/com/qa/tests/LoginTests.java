package com.qa.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.reports.ExtentReport;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest{
	
	LoginPage loginPage;
	ProductsPage productsPage;
	TestUtils utils = new TestUtils();
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais=null;
		try {
			String dataFieldName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFieldName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			if(datais!=null){
				datais.close();
			}
		}
		closeApp();
		launchApp();
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		utils.log("LoginTest Beefore method");
		loginPage = new LoginPage();
		productsPage = new ProductsPage();
		
		utils.log("\n"+"******* starting test:"+ m.getName()+"********"+"\n");
	}
	
	@AfterMethod
	public void afterMethod() {
		utils.log("LoginTest After method");
	}

	@AfterClass
	public void afterClass() {
	}

	@Test
	  public void invalidUserName() throws Exception {
		  loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		  loginPage.pressLoginButton();
		  
		  Thread.sleep(2000);

		  String actualErrorText = loginPage.getErrorText();
		  String expectedErrorText = getStrings().get("error_invalid_username_or_password");
		  utils.log("Actual Error Text - "+ actualErrorText+"\n"+"Expected error Text - "+expectedErrorText);
		  Assert.assertEquals(actualErrorText, expectedErrorText);	  
	  }
	  
	  @Test
	  public void invalidPassword() throws Exception {
		  loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		  loginPage.pressLoginButton();
		  
		  Thread.sleep(2000);
		  
		  String actualErrorText = loginPage.getErrorText();
		  String expectedErrorText = getStrings().get("error_invalid_username_or_password");
		  utils.log("Actual Error Text - "+ actualErrorText+"\n"+"Expected error Text - "+expectedErrorText);
		  Assert.assertEquals(actualErrorText, expectedErrorText);
	  }
	  
	  @Test
	  public void successfulLogin() throws Exception {
		  loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		  loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		  loginPage.pressLoginButton();
		  
		  Thread.sleep(2000);

		  String actualproductTitle = productsPage.getTitle();
		  String expectedproductTitle = getStrings().get("product_title");
		  utils.log("Actual Product Title - "+ actualproductTitle+"\n"+"Expected title - "+expectedproductTitle);
		  
		  Assert.assertEquals(actualproductTitle, expectedproductTitle);
	  }
}
