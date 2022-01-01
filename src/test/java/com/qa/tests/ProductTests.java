package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;

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

public class ProductTests extends BaseTest {

	LoginPage loginPage;
	ProductsPage productsPage;
	SettingsPage settingsPage;
	ProductDetailsPage productDetailsPage;	
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws Exception {
		InputStream datais=null;
		try {
			String dataFieldName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFieldName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (datais != null) {
				datais.close();
			}
		}
		closeApp();
		launchApp();
	}

	@BeforeMethod
	public void beforeMethod(Method m) throws Exception {
		loginPage = new LoginPage();
		productsPage = new ProductsPage();

		System.out.println("\n" + "******* starting test:" + m.getName() + "********" + "\n");

		productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
				loginUsers.getJSONObject("validUser").getString("password"));

		Thread.sleep(2000);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		settingsPage = productsPage.pressSettingsButton();
		loginPage = settingsPage.pressLogoutButton();
	}

	@AfterClass
	public void afterClass() {
	}

	@Test
	public void validateProductOnProductsPage() throws Exception {

		SoftAssert sa = new SoftAssert();

		String SLBTitle = productsPage.getSLBTitle();
		sa.assertEquals(SLBTitle, getStrings().get("products_page_slb_title"));

		String SLBPrice = productsPage.getSLBPrice();
		sa.assertEquals(SLBPrice, getStrings().get("products_page_slb_price"));

		sa.assertAll();
	}

	@Test
	public void validateProductOnProductDetailssPage() throws Exception {

		SoftAssert sa = new SoftAssert();

		productDetailsPage = productsPage.pressSLBTitle();

		String SLBTitle = productDetailsPage.getSLBTitle();
		sa.assertEquals(SLBTitle, getStrings().get("products_details_page_slb_title"));

		String SLBdescription = productDetailsPage.getSLBText();
		sa.assertEquals(SLBdescription, getStrings().get("products_details_page_slb_txt"));
		
		//String SLBPrice = productDetailsPage.scrollToSLBPriceAndGetSLBPrice();
		//sa.assertEquals(SLBPrice, strings.get("products_details_page_slb_price"));

		productDetailsPage.scrollPage();
		sa.assertTrue(productDetailsPage.isAddToCartBtnDisplayed());
		
		productsPage = productDetailsPage.pressBackToProductsButton();

		sa.assertAll();
	}
}
