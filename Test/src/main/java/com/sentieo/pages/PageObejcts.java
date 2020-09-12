package com.sentieo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sentieo.baseClass.LoggingUtils;

public class NewPageObject {

	WebDriver driver;

	@FindBy(name = "reg.email")
	WebElement _register_enterEmail;

	@FindBy(name = "reg.password")
	WebElement _register_enterPassword;

	@FindBy(name = "reg.confirmPassword")
	WebElement _register_confirmPassword;

	@FindBy(name = "_action_doRegister")
	WebElement _register_submitButton;

	@FindBy(xpath = "/html/body/div[2]")
	WebElement _getNextPageText;

	@FindBy(name = "fp.email")
	WebElement _forgetPassword_enterEmail;

	@FindBy(id = "forgotPasswordButton")
	WebElement _forgetPassword_SubmitButton;

	@FindBy(id = "fpSuccessModal")
	WebElement _forgetPassword_confirmPopup;

	@FindBy(css = ".modal-dialog .modal-footer .btn-primary")
	WebElement _forgetPassword_closePopup;

	@FindBy(name = "creds.username")
	WebElement _login_userName;

	@FindBy(name = "creds.password")
	WebElement _login_password;

	@FindBy(id = "loginButton")
	WebElement _login_LoginButton;

	public NewPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void registerUser(String userName, String password) throws InterruptedException {

		Actions actions = new Actions(driver);
		LoggingUtils.info("Enter UserName " + (userName));
		actions.moveToElement(_register_enterEmail).click().sendKeys(userName).build().perform();
		LoggingUtils.info("Enter password " + (password));
		actions.moveToElement(_register_enterPassword).click().sendKeys(password).build().perform();
		LoggingUtils.info("Enter confirm password " + (password));
		actions.moveToElement(_register_confirmPassword).click().sendKeys(password).build().perform();
		LoggingUtils.info("Submit the details ");
		actions.moveToElement(_register_submitButton).click().build().perform();
		new NewPageObject(driver);
		String getText = _getNextPageText.getText();
		if (getText.equalsIgnoreCase("Check your email for account verification instructions.")) {
			LoggingUtils.info("User registered successfully ");
		}
	}

	public void password_User(String userName) throws InterruptedException {

		Actions actions = new Actions(driver);
		LoggingUtils.info("Enter email address for password reset :" + (userName));
		actions.moveToElement(_forgetPassword_enterEmail).click().sendKeys(userName).build().perform();
		LoggingUtils.info("Submit the details ");
		actions.moveToElement(_forgetPassword_SubmitButton).click().build().perform();
		Thread.sleep(1000);
		new NewPageObject(driver);
		String getText = _forgetPassword_confirmPopup.getAttribute("style");
		if (getText.contains("display: block;")) {
			LoggingUtils.info("Close the confirmation popup :");
			actions.moveToElement(_forgetPassword_closePopup).click().build().perform();
		}
	}

	public void complete_Successful(String userName, String password) throws InterruptedException {

		Actions actions = new Actions(driver);
		LoggingUtils.info("Enter UserName " + (userName));
		actions.moveToElement(_login_userName).click().sendKeys(userName).build().perform();
		LoggingUtils.info("Enter password " + (password));
		actions.moveToElement(_login_password).click().sendKeys(password).build().perform();
		LoggingUtils.info("Submit the details ");
		actions.moveToElement(_login_LoginButton).click().build().perform();
		Thread.sleep(1000);
		new NewPageObject(driver);
		String getText = _getNextPageText.getText();
		if (getText.equalsIgnoreCase("You have successfully logged in.")) {
			LoggingUtils.info("User login successfully ");
		}
	}
}
