package Test;

import org.testng.annotations.Test;

import com.sentieo.baseClass.BaseClass;
import com.sentieo.pages.PageObejcts;

public class LibraryTest extends BaseClass {

	PageObejcts page = new PageObejcts(driver);

	@Test(groups = "sanity,regression,smoke", description = "open the URL and enter the email's")
	public void testFreeTrial() throws Exception {

		driver.get(appUrl);
		Thread.sleep(3000);
		page = new PageObejcts(driver);
		boolean getValue = page.getEmailCount("abc@gmail.com", "abc@carrothr.com", 2);
		if (getValue) {
			page = new PageObejcts(driver);
			page.verifyTheSecondPage("Check your email");
		}
	}

	@Test(groups = "sanity,regression,smoke", description = "verify the flow of feature", priority = 1)
	public void fetauretest() throws Exception {

		page = new PageObejcts(driver);
		boolean status = page.selectTheButton("Features", "Anniversaries & Birthdays");
		if (!status) {
			System.out.println("Anniversaries & Birthdays tab is not active yet");
		}
	}

	@Test(groups = "sanity,regression,smoke", description = "verify the complete flow of ", priority = 2)
	public void contactUs() throws Exception {

		page = new PageObejcts(driver);
		driver.switchTo().defaultContent();
		page.contactUS();
		page = new PageObejcts(driver);
		boolean status = page.setTextIntercom("slack");
		if (status) {
			boolean suggestionVisible = page.getSuggestions();
			if (!suggestionVisible) {
				System.out.println("Suggestion not visible");
			}
		} else {
			System.out.println("Intercom chat box not opened");
		}
	}
}
