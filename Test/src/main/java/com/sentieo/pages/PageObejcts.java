package com.sentieo.pages;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObejcts {

	WebDriver driver;

	@FindBy(css = ".input-group")
	List<WebElement> emailBox;

	@FindBy(css = ".form-inline .btn-tryFree")
	WebElement tryforFreeButton;

	@FindBy(css = ".readmin-page-content .jss12 .MuiTypography-root")
	List<WebElement> nextPageVerify;

	@FindBy(css = ".container .topbar-left .nav-item")
	List<WebElement> featureButtons;

	@FindBy(css = ".row.gap-5 .nav .nav-item .nav-link")
	List<WebElement> selectAnniversary;

	@FindBy(css = ".site-footer .container .launch_intercom")
	WebElement contactUsButton;

	@FindBy(css = ".intercom-messenger-frame")
	WebElement intercomView;

	@FindBy(css = ".intercom-1xafcqx .intercom-messenger-card-body .intercom-1naovro")
	WebElement intercomMsgSubmitButton;

	@FindBy(css = ".intercom-1xafcqx .intercom-messenger-card-body .intercom-1naovro")
	WebElement intercomMsgTextArea;

	@FindBy(css = ".intercom-1xafcqx .intercom-messenger-card-body .intercom-messenger-card-component .intercom-1xftouw .intercom-messenger-card-list-item")
	List<WebElement> intercomMsgSuggestion;

	public PageObejcts(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean getEmailCount(String wrongEmail, String validEmail, int number) throws InterruptedException {

		Actions actions = new Actions(driver);
		if (emailBox.size() > number) {
			System.out.println("Total number of Email text box : " + emailBox.size());
			for (int i = 0; i < emailBox.size(); i++) {
				if (i == (number)) {
					WebElement ele = emailBox.get(i).findElement(By.cssSelector(".fs-16.form-control-new"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
					actions.moveToElement(ele).click().sendKeys(wrongEmail).build().perform();
					actions.moveToElement(tryforFreeButton).click().build().perform();
					Thread.sleep(3000);
					String getPlaceHolder = ele.getAttribute("placeholder");
					if (getPlaceHolder.equals("Please try a work email")) {
						actions.moveToElement(ele).click().sendKeys(validEmail).build().perform();
						actions.moveToElement(tryforFreeButton).click().build().perform();
						Thread.sleep(3000);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean verifyTheSecondPage(String text) throws InterruptedException {
		for (int i = 0; i < nextPageVerify.size(); i++) {
			String getText = nextPageVerify.get(i).getText();
			if (getText.equals(text)) {
				driver.navigate().back();
				Thread.sleep(2000);
				return true;
			}
		}
		return false;
	}

	public boolean selectTheButton(String text, String ann) throws InterruptedException {
		for (int i = 0; i < featureButtons.size(); i++) {
			String getText = featureButtons.get(i).getText();
			if (getText.equals(text)) {
				featureButtons.get(i).click();
				for (int j = 0; j < selectAnniversary.size(); j++) {
					String getName = selectAnniversary.get(j).getText();
					String classState = selectAnniversary.get(j).getAttribute("class");
					if (getName.contains("Recognition")) {
						if (!classState.contains("active")) {
							assertTrue("By default recongnition is selected", false);
						}
					} else if (getName.contains(ann)) {
						selectAnniversary.get(j).click();
						Thread.sleep(2000);
						String getState = selectAnniversary.get(j).getAttribute("class");
						if (getState.contains("active")) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void contactUS() throws InterruptedException {

		Actions actions = new Actions(driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", contactUsButton);
		actions.moveToElement(contactUsButton).click().build().perform();
		Thread.sleep(2000);
	}

	public boolean getSuggestions() {

		if (intercomMsgSuggestion.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean setTextIntercom(String text) throws InterruptedException {
		Actions actions = new Actions(driver);
		if (intercomView.isDisplayed()) {
			driver.switchTo().frame("intercom-messenger-frame");
			JavascriptExecutor java = (JavascriptExecutor) driver;
			WebElement element = (WebElement) java.executeScript(
					"return  document.querySelectorAll('.intercom-1qgdrjz.ea4em4b0 div')[0].children[0]");
			actions.moveToElement(element).click().sendKeys(text).build().perform();
			actions.moveToElement(intercomMsgSubmitButton).click().build().perform();
			Thread.sleep(3000);
			return true;
		}
		return false;
	}
}
