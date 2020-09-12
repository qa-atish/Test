package Test;

import org.testng.annotations.Test;

import com.sentieo.baseClass.BaseClass;
import com.sentieo.pages.NewPageObject;

public class LibraryTest extends BaseClass {

	NewPageObject page = new NewPageObject(driver);

	@Test(groups = "sanity,regression,smoke", description = "verify the user registration flow")
	public void register_user() throws Exception {

		driver.get(appUrl);
		Thread.sleep(2000);
		page = new NewPageObject(driver);
		page.registerUser("jimmy_100@sify.com", "tomHarry1");
	}

	@Test(groups = "sanity,regression,smoke", description = "verify the forget password flow ")
	public void forget_password() throws Exception {

		driver.get("http://localhost:8080/WhatChaMaCallIt/login/forgotPassword");
		Thread.sleep(2000);
		page = new NewPageObject(driver);
		page.password_User("jimmy_100@sify.com");
	}

	@Test(groups = "sanity,regression,smoke", description = "verify that user is able to login to the app ")
	public void successful_Login() throws Exception {

		driver.get("http://localhost:8080/WhatChaMaCallIt/login/index");
		Thread.sleep(2000);
		page = new NewPageObject(driver);
		page.complete_Successful("quality@jukinmedia.com", "Test1ng");
	}
}
